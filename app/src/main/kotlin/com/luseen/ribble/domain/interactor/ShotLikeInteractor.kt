package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.repository.ShotDataRepository
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Like
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 05.08.2017.
 */
@PerActivity
class ShotLikeInteractor @Inject constructor(private val shotRepository: ShotDataRepository) {

    var shotLikeCount: Int = 0

    fun getShotLikes(shotId: String): Flowable<List<Like>> {
        return shotRepository.getShotLikes(shotId)
                .doOnNext { shotLikeCount = it.count() }
    }

    fun likeShot(shotId: String): Completable {
        return shotRepository.likeShot(shotId)
    }
}