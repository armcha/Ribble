package com.luseen.ribble.data.repository.data_store

import com.luseen.ribble.data.entity.LikeEntity
import com.luseen.ribble.data.entity.ShotEntity
import com.luseen.ribble.data.network.ApiService
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Singleton
class ApiDataStore @Inject constructor(private val apiService: ApiService) : ShotDataStore {

    override fun getShotList(count: Int): Flowable<List<ShotEntity>> {
        return apiService.getShots(count)
        //TODO("not implemented")
    }

    override fun getShotLikes(shotId: String): Flowable<List<LikeEntity>> {
        return apiService.getShotLikes(shotId)
    }
}