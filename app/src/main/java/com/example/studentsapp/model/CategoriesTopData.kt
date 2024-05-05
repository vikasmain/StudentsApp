package com.example.studentsapp.model

import com.google.gson.annotations.SerializedName

data class CategoriesTopData(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)
