package com.example.studentsapp.database

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.studentsapp.api.FeedApiService
import com.example.studentsapp.model.FeedRemoteKeys
import com.example.studentsapp.model.FeedResponse
import javax.inject.Inject

@ExperimentalPagingApi
class FeedRemoteMediator @Inject constructor(
    private val feedApiService: FeedApiService,
    private val feedDatabase: FeedDatabase
) : RemoteMediator<Int, FeedResponse.FeedItem>() {

    private val feedDao = feedDatabase.feedItemDao()
    private val remoteKeysDao = feedDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FeedResponse.FeedItem>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            //Fetching response from api
            val response = feedApiService.getFeedList(currentPage)
            val endOfPaginationReached = response.totalPages == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            //saving remote keys and data to db
            feedDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    feedDao.deleteFromFeed()
                    remoteKeysDao.deleteRemoteKeys()
                }

                feedDao.insertFeedItem(response.feedItem)
                val keys = response.feedItem.map { quote ->
                    FeedRemoteKeys(
                        id = quote.id,
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
                remoteKeysDao.addRemoteKeys(keys)
            }
            return MediatorResult.Success(endOfPaginationReached)
        } catch (e: Throwable) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, FeedResponse.FeedItem>
    ): FeedRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKey(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, FeedResponse.FeedItem>
    ): FeedRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { feedItem ->
                remoteKeysDao.getRemoteKey(id = feedItem.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, FeedResponse.FeedItem>
    ): FeedRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { feedITem ->
                remoteKeysDao.getRemoteKey(id = feedITem.id)
            }
    }
}
