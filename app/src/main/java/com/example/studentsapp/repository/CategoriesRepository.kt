package com.example.studentsapp.repository

import android.util.Log
import com.example.studentsapp.api.ApiService
import com.example.studentsapp.model.CategoriesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getCategories(): Flow<CategoriesResponse> {
        return flow {
            val data = apiService.getCategories()
            Log.d("RideAct",""+data)
            emit(data)
        }
    }
}
