package com.example.studentsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studentsapp.model.CategoriesResponse
import com.example.studentsapp.model.CategoriesResponse.Categories.CategoriesData

@Dao
interface CategoryDao {

    @Insert
    suspend fun addCategoryData(list: List<CategoriesData>)

    @Query("select * from categories")
    suspend fun getCategoriesList(): List<CategoriesData>
}
