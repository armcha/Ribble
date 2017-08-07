package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.ShotDataRepository
import com.luseen.ribble.data.network.ApiConstants
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.model.Shot
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 02.08.2017.
 */
@PerActivity
class ShotListInteractor @Inject constructor(private val shotRepository: ShotDataRepository) {

    fun getPopularShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_POPULAR, count)
    }

    fun getRecentShotList(count: Int = 500): Flowable<List<Shot>> {
        return shotRepository.getShotList(ApiConstants.TYPE_RECENT, count)
    }

}