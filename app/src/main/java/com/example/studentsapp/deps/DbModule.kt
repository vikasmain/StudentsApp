package com.example.studentsapp.deps

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.studentsapp.database.CategoryDatabase
import com.example.studentsapp.database.FeedDatabase
import com.example.studentsapp.model.FeedResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): FeedDatabase {
        return Room.databaseBuilder(context, FeedDatabase::class.java, "feeddb").build()
    }

    @Provides
    @Singleton
    fun providesCategoryDatabase(@ApplicationContext context: Context): CategoryDatabase =
        Room.databaseBuilder(context, CategoryDatabase::class.java, "categoryDb")
            .build()
}
