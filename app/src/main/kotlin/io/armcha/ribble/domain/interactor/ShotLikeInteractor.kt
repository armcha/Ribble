package io.armcha.ribble.domain.interactor

import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.domain.repository.ShotRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 05.08.2017.
 */
@PerActivity
class ShotLikeInteractor @Inject constructor(private val shotRepository: ShotRepository) {

    var shotLikeCount: Int = 0

    fun getShotLikes(shotId: String): Flowable<List<Like>> {
        return shotRepository.getShotLikes(shotId)
                .doOnNext { shotLikeCount = it.count() }
    }

    fun likeShot(shotId: String) = shotRepository.likeShot(shotId)
}