package com.example.studentsapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentsapp.model.FeedRemoteKeys

@Dao
interface RemoteKeysDao {

    @Query("Select * from FeedRemoteKeys WHERE id= :id")
    suspend fun getRemoteKey(id: Int): FeedRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(feedList: List<FeedRemoteKeys>)

    @Query("Delete from FeedRemoteKeys")
    suspend fun deleteRemoteKeys()
}