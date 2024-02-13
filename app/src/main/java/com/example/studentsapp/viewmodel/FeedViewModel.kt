package com.example.studentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.studentsapp.api.FeedApiService
import com.example.studentsapp.database.FeedDatabase
import com.example.studentsapp.database.FeedRemoteMediator
import com.example.studentsapp.repository.FeedPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedApiService: FeedApiService,
    private val feedDatabase: FeedDatabase
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 5, maxSize = 100),
        remoteMediator = FeedRemoteMediator(feedApiService, feedDatabase),
        //maxSize = number of pages to keep in in memory cache
        pagingSourceFactory = { FeedPagingSource(feedApiService) }
    ).flow
}
