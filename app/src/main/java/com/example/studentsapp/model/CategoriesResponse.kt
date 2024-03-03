package com.example.studentsapp.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: List<CategoriesData>
) {
    data class CategoriesData(
        @SerializedName("index") val index: Int,
        @SerializedName("title") val title: String,
        @SerializedName("items") val items: CategoriesItem
    ) {
        data class CategoriesItem(
            @SerializedName("title") val title: String,
            @SerializedName("description") val description: String,
            @SerializedName("type") val type: Type
        )
    }
}

enum class Type(val type: String) {
    IMPORTANT("important"),
    ACTUAL("actual"),
}