package com.example.studentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.studentsapp.api.ApiService
import com.example.studentsapp.database.FeedDatabase
import com.example.studentsapp.database.FeedRemoteMediator
import com.example.studentsapp.repository.FeedPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val apiService: ApiService,
    private val feedDatabase: FeedDatabase
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 5, maxSize = 100),
        remoteMediator = FeedRemoteMediator(apiService, feedDatabase),
        //maxSize = number of pages to keep in in memory cache
        pagingSourceFactory = { FeedPagingSource(apiService) }
    ).flow
}
