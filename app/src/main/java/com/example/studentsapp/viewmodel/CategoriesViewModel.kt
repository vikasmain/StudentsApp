package com.example.studentsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentsapp.data.CategoryItemData
import com.example.studentsapp.model.CategoriesData
import com.example.studentsapp.model.CategoriesResponse
import com.example.studentsapp.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    init {
        getCategories()
    }

    val feedListStateFlow = MutableStateFlow<List<CategoryItemData>?>(null)

    fun getCategories() {
        viewModelScope.launch {
            categoriesRepository.getCategories()
                .map {
                    mapCategoriesItemData(it)
                }
                .catch {
                    feedListStateFlow.value = null
                }.onStart {
                }
                .onEach {
                    feedListStateFlow.value = it
                }
                .collect()
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
