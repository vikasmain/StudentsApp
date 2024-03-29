package com.example.studentsapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentsapp.model.FeedResponse

@Dao
interface FeedItemDao {

    @Query("Select * from FeedItem")
    fun getFeed(): PagingSource<Int, FeedResponse.FeedItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedItem(feedList: List<FeedResponse.FeedItem>)

    @Query("Delete from FeedItem")
    suspend fun deleteFromFeed()
}