package com.example.studentsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Categories
) {
    data class Categories(
        @SerializedName("categories") val categories: List<CategoriesData>
    )
}

enum class Type(val type: String) {
    IMPORTANT("important"),
    ACTUAL("actual"),
}