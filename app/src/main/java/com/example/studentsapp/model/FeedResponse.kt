package com.example.studentsapp.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("data") val data: Boolean,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("feed_item") val feedItem: List<FeedItem>
) {
    data class FeedItem(
        @SerializedName("data") val data: Boolean,
        @SerializedName("id") val id: Int
    )
}
