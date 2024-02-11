package com.example.studentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.studentsapp.api.FeedApiService
import com.example.studentsapp.repository.FeedPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedApiService: FeedApiService
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 5, maxSize = 100),
        //maxSize = number of pages to keep in in memory cache
        pagingSourceFactory = { FeedPagingSource(feedApiService) }
    ).flow
}
