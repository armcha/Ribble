package com.luseen.ribble.data.network

import com.luseen.ribble.data.entity.UserEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface UserApiService {

    @GET("user")
    fun getUser(@Query("access_token") token: String = ApiConstants.TOKEN):Flowable<UserEntity>
}