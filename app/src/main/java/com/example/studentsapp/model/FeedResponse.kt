package com.example.studentsapp.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("data") val data: Boolean
)
