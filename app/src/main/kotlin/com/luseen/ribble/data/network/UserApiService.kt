package com.luseen.ribble.data.network

import com.luseen.ribble.data.response.LikeResponse
import com.luseen.ribble.data.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface UserApiService {

    @GET("user")
    fun getUser(@Query("access_token") token: String = ApiConstants.TOKEN): Flowable<UserResponse>

    @PUT("v1/users/{user}/follow")
    fun follow(@Path("user") username: String): Completable

    @GET("user/likes")
    fun getUserLikes(@Query("per_page") pageSize: Int = 100,
                     @Query("page") page: Int = 1,
                     @Query("access_token") token: String = ApiConstants.TOKEN): Single<List<LikeResponse>>
}