package com.luseen.ribble.data.network

import com.luseen.ribble.data.response.CommentResponse
import com.luseen.ribble.data.response.LikeResponse
import com.luseen.ribble.data.response.ShotResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

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
    fun getShotComments(@Path("shot_id") shotId: String): Single<List<CommentResponse>>

    @POST("shots/{shot_id}/like")
    fun likeShot(@Path("shot_id") shotId: String): Completable

    @DELETE("shots/{shot_id}/like")
    fun unLikeShot(@Path("shot_id") shotId: String): Completable
}