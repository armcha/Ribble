package io.armcha.ribble.domain.interactor

import io.armcha.ribble.data.cache.MemoryCache
import io.armcha.ribble.data.network.ApiConstants
import io.armcha.ribble.data.repository.ShotDataRepository
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.fetcher.result_listener.RequestType
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 02.08.2017.
 */
@PerActivity
class ShotListInteractor @Inject constructor(private val shotRepository: ShotDataRepository,
                                             private val memoryCache: MemoryCache) {

    fun popularShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_POPULAR, count)
    }

    fun recentShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_RECENT, count)
    }

    fun popularShotFromMemory(): List<Shot> = memoryCache getCacheForType RequestType.POPULAR_SHOTS

    fun recentShotFromMemory(): List<Shot> = memoryCache getCacheForType RequestType.RECENT_SHOTS
}