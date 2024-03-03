package com.example.studentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentsapp.data.CategoryItemData
import com.example.studentsapp.model.CategoriesResponse
import com.example.studentsapp.model.FeedResponse
import com.example.studentsapp.repository.CategoriesRepository
import com.example.studentsapp.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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
                .onEach {
                    feedListStateFlow.value = it
                }
                .catch {
                    feedListStateFlow.value = null
                }
        }
    }

    private fun mapCategoriesItemData(it: CategoriesResponse): List<CategoryItemData> {
        val categoriesItemDataList = mutableListOf<CategoryItemData>()
        if (it.data.isEmpty().not()) {
            categoriesItemDataList.add(CategoryItemData.SearchBar)
        }
        it.data.sortedBy { it.index }.forEach {
            categoriesItemDataList.add(
                CategoryItemData.CategoryHeader(
                    title = it.title,
                    description = it.description
                )
            )
            it.items.forEach {
                categoriesItemDataList.add(
                    CategoryItemData.CategoryItem(
                        title = it.title,
                        description = it.description,
                        likes = it.likes,
                        comments = it.comments
                    )
                )
            }
        }
        return categoriesItemDataList
    }
}
