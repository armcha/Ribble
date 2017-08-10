package com.luseen.ribble.data.network

import io.reactivex.Flowable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface UserApiService {

    @POST("token")
    fun getToken(@Query("code") authCode: String,
                 @Query("client_id") clientId: String = ApiConstants.CLIENT_ID,
                 @Query("client_secret") clientSecret: String = ApiConstants.CLIENT_SECRET): Flowable<String>
}