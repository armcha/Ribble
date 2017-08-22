package com.luseen.ribble.data.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Chatikyan on 22.08.2017.
 */
class TokenInterceptor constructor(private val accessToken: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + accessToken)
                .build()
        return chain.proceed(request)
    }
}