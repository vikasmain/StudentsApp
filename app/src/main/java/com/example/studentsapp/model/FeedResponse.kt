package com.example.studentsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("data") val data: Boolean,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("feed_item") val feedItem: List<FeedItem>
) {
    @Entity(tableName = "FeedItem")
    data class FeedItem(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id") val id: Int,
        @SerializedName("data") val data: Boolean,
    )
}
