package com.luseen.ribble.di.module

import android.app.Application
import com.luseen.ribble.BuildConfig
import com.luseen.ribble.data.network.ApiService
import com.luseen.ribble.data.pref.Preferences
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun providePreferences(application: Application): Preferences {
        return Preferences(application)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}