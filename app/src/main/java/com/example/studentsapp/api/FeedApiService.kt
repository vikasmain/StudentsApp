package com.example.studentsapp.api

import com.example.studentsapp.model.FeedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApiService {

    @GET("/feed-response")
    suspend fun getFeedList(@Query("page_number") pageNumber: Int): FeedResponse
}