package com.example.studentsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FeedRemoteKeys")
data class FeedRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: String?,
    val nextKey: String?
)