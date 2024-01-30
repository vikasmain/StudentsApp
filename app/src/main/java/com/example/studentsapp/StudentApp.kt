package com.example.studentsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StudentApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
