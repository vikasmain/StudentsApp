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
    ) {
        @Entity(tableName = "categories")
        data class CategoriesData(
            @PrimaryKey(autoGenerate = true)
            val categoryId: Int,
            @SerializedName("index") val index: Int,
            @SerializedName("title") val title: String,
            @SerializedName("description") val description: String?,
            @SerializedName("items") val items: List<CategoriesItem>
        ) {
            data class CategoriesItem(
                @SerializedName("title") val title: String,
                @SerializedName("description") val description: String?,
                @SerializedName("type") val type: Type?,
                @SerializedName("likes") val likes: Int?,
                @SerializedName("comments") val comments: Int?
            )
        }
    }
}

enum class Type(val type: String) {
    IMPORTANT("important"),
    ACTUAL("actual"),
}