package com.example.studentsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentsapp.data.CategoryItemData
import com.example.studentsapp.model.CategoriesData
import com.example.studentsapp.model.CategoriesDataModel
import com.example.studentsapp.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {


    private val feedListStateFlow = MutableStateFlow<CategoriesDataModel?>(null)

    val categoriesFlow: MutableStateFlow<CategoriesDataModel?>
        get() = feedListStateFlow

    fun getCategories() {
        viewModelScope.launch {
            supervisorScope {
                val categoriesDataDeferred = async {
                    categoriesRepository.getCategories().map {
                        mapCategoriesItemData(it)
                    }
                }
                val topDataDeferred = async { categoriesRepository.getTopData() }
                val categoriesData = try {
                    categoriesDataDeferred.await()
                } catch (e: Exception) {
                    Log.e("SearchViewModel", "Error message $e")
                    null
                }
                val topData = try {
                    topDataDeferred.await()
                } catch (e: Exception) {
                    Log.e("SearchViewModel", "Error message $e")
                    null
                }
                feedListStateFlow.value = CategoriesDataModel(
                    categoriesTopData = topData?.first(),
                    categoriesItem = categoriesData?.first()
                )
            }
        }
    }

    //parallel api calls using zip operator, zip operator returns result of multiplr tasks in single callback when all the tasks are completed.

    fun getCategoriesUsingZipOperator() {
        viewModelScope.launch {
            categoriesRepository.getCategories().map {
                mapCategoriesItemData(it)
            }.zip(categoriesRepository.getTopData()) { categoryItemData, categoriesTopData ->
                feedListStateFlow.value = CategoriesDataModel(
                    categoriesTopData = categoriesTopData,
                    categoriesItem = categoryItemData
                )
            }
        }
    }

    fun getSearchQueryData() {

    }

    private fun mapCategoriesItemData(it: List<CategoriesData>): List<CategoryItemData> {
        val categoriesItemDataList = mutableListOf<CategoryItemData>()
        if (it.isEmpty().not()) {
            categoriesItemDataList.add(CategoryItemData.SearchBar)
        }
        it.forEach {
            categoriesItemDataList.add(
                CategoryItemData.CategoryHeader(
                    title = it.title,
                    description = it.description
                )
            )
            it.items.forEach { categoryItem ->
                categoriesItemDataList.add(
                    CategoryItemData.CategoryItem(
                        title = categoryItem.title,
                        description = categoryItem.description,
                        likes = categoryItem.likes,
                        comments = categoryItem.comments
                    )
                )
            }
        }
        return categoriesItemDataList
    }
}
