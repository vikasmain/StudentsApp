package com.example.studentsapp.deps

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.api.FeedApiService
import com.example.studentsapp.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://web.com/"

@Module
@InstallIn(SingletonComponent::class)
class StudentsAppModule {

    companion object {
        @Provides
        @Singleton
        fun providesActivityMainBinding(activity: AppCompatActivity): ActivityMainBinding {
            return ActivityMainBinding.inflate(LayoutInflater.from(activity))
        }

        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
        }

        @Singleton
        @Provides
        fun provideConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

        @Singleton
        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
        }

        @Singleton
        @Provides
        fun provideCurrencyService(retrofit: Retrofit): FeedApiService =
            retrofit.create(FeedApiService::class.java)
    }
}
