package com.example.studentsapp.utils

import android.content.Context
import android.net.NetworkCapabilities
import android.net.ConnectivityManager
import javax.inject.Inject

class Network @Inject constructor() {

    fun isNetworkAvailable(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) ?: false
        }
    }
}
