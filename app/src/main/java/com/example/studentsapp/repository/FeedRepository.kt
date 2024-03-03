package com.example.studentsapp.repository

import com.example.studentsapp.api.ApiService
import com.example.studentsapp.model.FeedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getFeedList(pageNumber: Int): Flow<FeedResponse> {
        return flow {
            val data = apiService.getFeedList(pageNumber)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}
