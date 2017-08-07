package com.luseen.ribble.data.network

import com.luseen.ribble.data.entity.LikeEntity
import com.luseen.ribble.data.entity.ShotEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Chatikyan on 29.07.2017.
 */
interface ApiService {

    @GET("shots?")
    fun getShots(@Query("per_page") limit: Int,
                 @Query("sort") sortType: String,
                 @Query("access_token") token: String = ApiConstants.TOKEN): Flowable<List<ShotEntity>>

    @GET("shots/{shot_id}/likes?")
    fun getShotLikes(@Path("shot_id") shotId: String,
                     @Query("per_page") limit: Int = 500,
                     @Query("access_token") token: String = ApiConstants.TOKEN): Flowable<List<LikeEntity>>

    @GET("shots/{shot_id}/comments?")
    fun getShotComments(@Path("shot_id") shotId: String,
                        @Query("access_token") token: String = ApiConstants.TOKEN)

}