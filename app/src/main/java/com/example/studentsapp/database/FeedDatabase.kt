package com.example.studentsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studentsapp.model.FeedRemoteKeys
import com.example.studentsapp.model.FeedResponse

@Database(entities = [FeedResponse.FeedItem::class, FeedRemoteKeys::class], version = 1)
abstract class FeedDatabase : RoomDatabase() {
}