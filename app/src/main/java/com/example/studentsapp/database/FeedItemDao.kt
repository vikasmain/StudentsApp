package com.example.studentsapp.database

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentsapp.model.FeedResponse

interface FeedItemDao {

    @Query("Select * from FeedItem")
    suspend fun getFeed(): PagingSource<Int, FeedResponse.FeedItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedItem(feedList: List<FeedResponse.FeedItem>)

    @Query("Delete from FeedItem")
    suspend fun deleteFromFeed()
}