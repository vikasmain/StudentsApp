package com.example.studentsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.studentsapp.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class StudentsAppViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {

    val feedListStateFlow = MutableStateFlow(false)

    fun getFeedData() {
        feedRepository.getFeedList()
    }
}
