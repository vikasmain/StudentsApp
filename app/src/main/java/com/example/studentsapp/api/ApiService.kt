package com.example.studentsapp.api

import com.example.studentsapp.model.CategoriesResponse
import com.example.studentsapp.model.FeedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/feed-response")
    suspend fun getFeedList(@Query("page_number") pageNumber: Int): FeedResponse

    @GET("categories")
    suspend fun getCategories(): CategoriesResponse
}
