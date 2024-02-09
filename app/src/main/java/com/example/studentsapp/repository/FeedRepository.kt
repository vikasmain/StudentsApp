package com.example.studentsapp.repository

import com.example.studentsapp.api.FeedApiService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val feedApiService: FeedApiService
) {

    fun getFeedList() {
        flowOf()
feedApiService.getFeedList()
    }
}
