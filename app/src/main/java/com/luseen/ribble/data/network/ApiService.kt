package com.luseen.ribble.data.network

import com.luseen.ribble.data.entity.Shot
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Chatikyan on 29.07.2017.
 */
interface ApiService {

    @GET("shots?")
    fun getShots(@Query("per_page") limit: Int = 100, @Query("access_token") token: String):Flowable<MutableList<Shot>>
}