package com.luseen.ribble.presentation.screen.shot_detail

import com.luseen.logger.Logger
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.interactor.ShotDetailInteractor
import com.luseen.ribble.domain.interactor.ShotLikeInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.model.Like
import javax.inject.Inject

/**
 * Created by Chatikyan on 05.08.2017.
 */
@PerActivity
class ShotDetailPresenter @Inject constructor(private val shotDetailInteractor: ShotDetailInteractor,
                                              private val shotLikeInteractor: ShotLikeInteractor)
    : ApiPresenter<ShotDetailContract.View>(), ShotDetailContract.Presenter {

    init {

    }

    override fun fetchLikes(shotId: String?) {
        this fetch shotLikeInteractor.getShotLikes(shotId!!)
    }

    override fun onRequestStart() {

    }

    override fun <T> onRequestSuccess(data: T) {
        val likes: List<Like> = data as List<Like>
        Logger.log("Likes count is ${likes.count()}")
    }

    override fun onRequestError(errorMessage: String?) {

    }
}