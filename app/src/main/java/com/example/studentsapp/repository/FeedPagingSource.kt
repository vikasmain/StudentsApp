package com.example.studentsapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.studentsapp.api.FeedApiService
import com.example.studentsapp.model.FeedResponse

class FeedPagingSource(private val feedApiService: FeedApiService) :
    PagingSource<Int, FeedResponse.FeedItem>() {
    override fun getRefreshKey(state: PagingState<Int, FeedResponse.FeedItem>): Int? {
        //anchor position = recently accessed page.
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedResponse.FeedItem> {
        return try {
            val pagePosition = params.key ?: 1 // position of page to be load
            val data = feedApiService.getFeedList(pagePosition)
            LoadResult.Page(
                data = data.feedItem,
                nextKey = getNextKey(pagePosition, data),
                prevKey = getPrevKey(pagePosition)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    private fun getPrevKey(pagePosition: Int) = if (pagePosition == 1) {
        null
    } else {
        pagePosition - 1
    }

    private fun getNextKey(
        pagePosition: Int,
        data: FeedResponse
    ) = if (pagePosition == data.totalPages) {
        null
    } else {
        pagePosition + 1
    }
}
