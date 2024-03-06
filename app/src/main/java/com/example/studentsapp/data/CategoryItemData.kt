package com.example.studentsapp.data

sealed class CategoryItemData {

    data class CategoryHeader(
        val title: String,
        val description: String?
    ) : CategoryItemData()

    data class CategoryItem(
        val title: String,
        val description: String?,
        val comments: Int?,
        val likes: Int?
    ) : CategoryItemData()

    data object SearchBar : CategoryItemData()
}
