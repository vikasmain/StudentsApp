package com.example.studentsapp.repository

import com.example.studentsapp.api.FeedApiService
import com.example.studentsapp.model.FeedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val feedApiService: FeedApiService
) {
    fun getFeedList(pageNumber: Int): Flow<FeedResponse> {
        return flow {
            val data = feedApiService.getFeedList(pageNumber)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}
