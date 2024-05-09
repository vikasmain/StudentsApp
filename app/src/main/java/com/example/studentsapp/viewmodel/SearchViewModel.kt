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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
            try {
                val categoriesDataDeferred = async {
                    categoriesRepository.getCategories().map {
                        mapCategoriesItemData(it)
                    }
                }
                val topDataDeferred = async { categoriesRepository.getTopData() }
                val categoriesData = categoriesDataDeferred.await()
                val topData = topDataDeferred.await()
                feedListStateFlow.value = CategoriesDataModel(
                    categoriesTopData = topData.first(),
                    categoriesItem = categoriesData.first()
                )
            } catch (e: Exception) {
                Log.e("CategoryViewModel", "Error" + e.message)
            }
        }
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
