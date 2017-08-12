package com.luseen.ribble.data.repository

import com.luseen.ribble.data.mapper.Mapper
import com.luseen.ribble.data.network.ShotApiService
import com.luseen.ribble.data.pref.Preferences
import com.luseen.ribble.domain.repository.ShotRepository
import com.luseen.ribble.presentation.model.Like
import com.luseen.ribble.presentation.model.Shot
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 02.08.2017.
 */
@Singleton
class ShotDataRepository @Inject constructor(private var shotApiService: ShotApiService,
                                             private var preferences: Preferences,
                                             private var mapper: Mapper) : ShotRepository {

    override fun getShotList(shotType: String, count: Int): Flowable<List<Shot>> {
        return shotApiService.getShots(shotType, count).map { mapper.translate(it) }
    }

    override fun getShotLikes(shotId: String): Flowable<List<Like>> {
        return shotApiService.getShotLikes(shotId).map { mapper.translate(it) }
    }
}