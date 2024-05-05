package com.example.studentsapp.model

import com.example.studentsapp.data.CategoryItemData

data class CategoriesDataModel(
    val categoriesTopData: CategoriesTopData,
    val categoriesItem: List<CategoryItemData>
)
