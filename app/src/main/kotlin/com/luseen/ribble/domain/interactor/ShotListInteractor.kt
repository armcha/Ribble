package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.cache.MemoryCache
import com.luseen.ribble.data.network.ApiConstants
import com.luseen.ribble.data.repository.ShotDataRepository
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.fetcher.result_listener.RequestType
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 02.08.2017.
 */
@PerActivity
class ShotListInteractor @Inject constructor(private val shotRepository: ShotDataRepository,
                                             private val memoryCache: MemoryCache) {

    fun getPopularShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_POPULAR, count)
    }

    fun getRecentShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_RECENT, count)
    }

    fun getPopularShotFromMemory(): List<Shot> = memoryCache getCacheForType RequestType.POPULAR_SHOTS

    fun getRecentShotFromMemory(): List<Shot> = memoryCache getCacheForType RequestType.RECENT_SHOTS
}