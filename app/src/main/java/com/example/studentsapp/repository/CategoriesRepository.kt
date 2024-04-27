package com.example.studentsapp.repository

import android.content.Context
import com.example.studentsapp.api.ApiService
import com.example.studentsapp.database.CategoryDatabase
import com.example.studentsapp.model.CategoriesData
import com.example.studentsapp.utils.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil,
    @ApplicationContext private val applicationContext: Context,
    private val categoryDatabase: CategoryDatabase
) {

    fun getCategories(): Flow<List<CategoriesData>> {
        return flow {
            if (networkUtil.isNetworkAvailable(applicationContext)) {
                val data = apiService.getCategories()
                categoryDatabase.getCategoryDao().addCategoryData(data.data.categories)
                emit(data.data.categories)
            } else {
                //Might throw NPE if items list is empty in db
                val list = categoryDatabase.getCategoryDao().getCategoriesList()
                emit(list)
            }
        }
    }
}
