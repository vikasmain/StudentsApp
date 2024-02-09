package com.example.studentsapp.api

import com.example.studentsapp.model.FeedResponse
import retrofit2.http.GET

interface FeedApiService {

    @GET("/feed-response")
    suspend fun getFeedList(): FeedResponse
}