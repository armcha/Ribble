package io.armcha.ribble.domain.interactor

import io.armcha.ribble.data.network.ApiConstants
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.repository.ShotRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 02.08.2017.
 */
@PerActivity
class ShotListInteractor @Inject constructor(private val shotRepository: ShotRepository) {

    fun popularShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_POPULAR, count)
    }

    fun recentShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_RECENT, count)
    }
}