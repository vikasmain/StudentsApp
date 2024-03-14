package com.example.studentsapp.repository

import com.example.studentsapp.api.ApiService
import com.example.studentsapp.database.CategoryDatabase
import com.example.studentsapp.model.CategoriesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val apiService: ApiService,
    private val categoryDatabase: CategoryDatabase
) {

    fun getCategories(): Flow<CategoriesResponse> {
        return flow {
            val data = apiService.getCategories()
            categoryDatabase.getCategoryDao().addCategoryData(data.data.categories)
            emit(data)
        }
    }
}
