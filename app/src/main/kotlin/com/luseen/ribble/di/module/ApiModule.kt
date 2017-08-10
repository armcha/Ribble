package com.luseen.ribble.di.module

import com.luseen.ribble.BuildConfig
import com.luseen.ribble.data.network.ApiConstants
import com.luseen.ribble.data.network.ShotApiService
import com.luseen.ribble.data.network.UserApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Chatikyan on 04.08.2017.
 */
@Module
class ApiModule {

    @Singleton
    @Provides
    @Named("shotEndpoint")
    fun shotEndpoint(): String {
        return ApiConstants.SHOT_ENDPOINT
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            okHttpBuilder.addInterceptor(logging)
        }
        okHttpBuilder.readTimeout(15.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(15.toLong(), TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    @Named("shotRetrofit")
    fun provideShotRetrofit(retrofitBuilder: Retrofit.Builder,
                            @Named("shotEndpoint") baseUrl: String): Retrofit {
        return retrofitBuilder
                .baseUrl(baseUrl)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideShotApiService(@Named("shotRetrofit") retrofit: Retrofit): ShotApiService {
        return retrofit.create(ShotApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApiService(@Named("shotRetrofit") retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }
}