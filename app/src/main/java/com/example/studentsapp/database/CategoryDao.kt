package com.example.studentsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studentsapp.model.CategoriesData

@Dao
interface CategoryDao {

    @Insert
    suspend fun addCategoryData(data: List<CategoriesData>)

    @Query("select * from categoryData")
    suspend fun getCategoriesList(): List<CategoriesData>
}
