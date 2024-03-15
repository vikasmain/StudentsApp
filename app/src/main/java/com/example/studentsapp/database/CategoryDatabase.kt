package com.example.studentsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studentsapp.model.ArrayListConverter
import com.example.studentsapp.model.CategoriesData

@TypeConverters(ArrayListConverter::class)
@Database(entities = [CategoriesData::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

}
