package com.luseen.ribble.data.network

import com.luseen.ribble.data.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface UserApiService {

    @GET("user")
    fun getUser(@Query("access_token") token: String = ApiConstants.TOKEN):Flowable<UserResponse>

    @PUT("v1/users/{user}/follow")
    fun follow(@Path("user") username: String): Completable
}