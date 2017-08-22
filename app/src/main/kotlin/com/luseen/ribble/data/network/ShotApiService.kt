package com.luseen.ribble.data.network

import com.luseen.ribble.data.response.LikeResponse
import com.luseen.ribble.data.response.ShotResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Chatikyan on 29.07.2017.
 */
interface ShotApiService {

    @GET("shots?")
    fun getShots(@Query("sort") sortType: String,
                 @Query("per_page") limit: Int): Flowable<List<ShotResponse>>

    @GET("shots/{shot_id}/likes?")
    fun getShotLikes(@Path("shot_id") shotId: String,
                     @Query("per_page") limit: Int = 500): Flowable<List<LikeResponse>>

    @GET("shots/{shot_id}/comments?")
    fun getShotComments(@Path("shot_id") shotId: String)

}