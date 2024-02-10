package com.example.studentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentsapp.model.FeedResponse
import com.example.studentsapp.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsAppViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {

    val feedListStateFlow = MutableStateFlow<FeedResponse?>(null)

    fun getFeedData() {
        viewModelScope.launch {
            feedRepository.getFeedList()
                .onEach {
                    feedListStateFlow.value = it
                }
                .catch {
                    feedListStateFlow.value = null
                }
        }
    }
}
