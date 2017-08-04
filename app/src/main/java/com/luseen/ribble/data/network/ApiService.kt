package com.luseen.ribble.data.network

import com.luseen.ribble.data.entity.ShotEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Chatikyan on 29.07.2017.
 */
interface ApiService {

    @GET("shots?")
    fun getShots(@Query("per_page") limit: Int,
                 @Query("access_token") token: String = ApiConstants.TOKEN):Flowable<List<ShotEntity>>
}