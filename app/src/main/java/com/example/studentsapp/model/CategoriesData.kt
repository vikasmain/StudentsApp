package com.example.studentsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "categoryData")
data class CategoriesData(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int,
    @SerializedName("index") val index: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("items") val items: List<CategoriesItem>
) {
    data class CategoriesItem(
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String?,
        @SerializedName("likes") val likes: Int?,
        @SerializedName("comments") val comments: Int?
    )
}
