package com.example.studentsapp.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Categories
) {
    data class Categories(
        @SerializedName("categories") val categories: List<CategoriesData>
    )
}
