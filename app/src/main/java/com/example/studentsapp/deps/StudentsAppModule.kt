package com.example.studentsapp.deps

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class StudentsAppModule {

    companion object {
        @Provides
        @Singleton
        fun providesActivityMainBinding(activity: AppCompatActivity): ActivityMainBinding {
            return ActivityMainBinding.inflate(LayoutInflater.from(activity))
        }
    }
}
