package com.example.studentsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studentsapp.model.ArrayListConverter
import com.example.studentsapp.model.CategoriesData

@TypeConverters(ArrayListConverter::class)
@Database(entities = [CategoriesData::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    companion object {

        @Volatile
        private var instance: CategoryDatabase? = null

        fun getDatabase(context: Context): CategoryDatabase? {
            if (instance == null) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(
                            context,
                            CategoryDatabase::class.java,
                            "categoryDB"
                        ).build()
                }
            }
            return instance
        }
    }
}
