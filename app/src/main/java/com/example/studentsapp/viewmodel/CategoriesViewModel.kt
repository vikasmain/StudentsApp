package com.example.studentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentsapp.model.CategoriesResponse
import com.example.studentsapp.model.FeedResponse
import com.example.studentsapp.repository.CategoriesRepository
import com.example.studentsapp.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    val feedListStateFlow = MutableStateFlow<CategoriesResponse?>(null)

    fun getCategories() {
        viewModelScope.launch {
            categoriesRepository.getCategories()
                .onEach {
                    feedListStateFlow.value = it
                }
                .catch {
                    feedListStateFlow.value = null
                }
        }
    }
}
