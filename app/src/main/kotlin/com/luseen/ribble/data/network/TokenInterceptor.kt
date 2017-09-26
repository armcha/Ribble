package com.luseen.ribble.data.network

import com.luseen.ribble.data.pref.Preferences
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Chatikyan on 22.08.2017.
 */
class TokenInterceptor constructor(private val preferences: Preferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = if (preferences.isUserLoggedIn)
            preferences.token
        else
            ApiConstants.TOKEN
        val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        return chain.proceed(request)
    }
}