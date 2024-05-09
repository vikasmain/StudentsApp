package com.example.studentsapp.model

import com.google.gson.annotations.SerializedName

data class CategoriesTopData(
    @SerializedName("categories_top_data") val categoriesData: CategoriesTopDataItem
) {
    data class CategoriesTopDataItem(
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String
    )
}
