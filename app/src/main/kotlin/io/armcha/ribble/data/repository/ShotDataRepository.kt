package io.armcha.ribble.data.repository

import io.armcha.ribble.data.network.ApiConstants
import io.armcha.ribble.data.network.ShotApiService
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Comment
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.fetcher.result_listener.RequestType
import io.armcha.ribble.domain.repository.ShotRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 02.08.2017.
 */
@PerActivity
class ShotDataRepository @Inject constructor(private var shotApiService: ShotApiService)
    : ShotRepository, Repository() {

    override fun getShotList(shotType: String, count: Int): Flowable<List<Shot>> {
        val requestType = if (shotType == ApiConstants.TYPE_POPULAR)
            RequestType.POPULAR_SHOTS
        else
            RequestType.RECENT_SHOTS

        return if (memoryCache.hasCacheFor(requestType)) {
            Flowable.fromCallable<List<Shot>> { memoryCache.getCacheForType(requestType) }
        } else {
            shotApiService.getShots(shotType, count)
                    .map { mapper.translate(it) }
                    .doOnNext { memoryCache.put(requestType, it) }
        }
    }

    override fun getShotLikes(shotId: String): Flowable<List<Like>> {
        return shotApiService.getShotLikes(shotId)
                .map { mapper.translate(it) }
    }

    override fun getShotComments(shotId: String): Single<List<Comment>> =
            if (memoryCache.hasCacheFor(RequestType.COMMENTS)) {
                Single.fromCallable<List<Comment>> { memoryCache.getCacheForType(RequestType.COMMENTS) }
            } else {
                shotApiService.getShotComments(shotId)
                        .map { mapper.translate(it) }
                        .doOnSuccess { memoryCache.put(RequestType.COMMENTS, it) }
            }

    override fun likeShot(shotId: String) = shotApiService.likeShot(shotId)

    override fun deleteCacheFor(requestType: RequestType) {
        memoryCache.clearCacheFor(requestType)
    }
}