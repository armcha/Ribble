package com.luseen.ribble.presentation.screen.shot_detail

import com.luseen.logger.Logger
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.interactor.ShotDetailInteractor
import com.luseen.ribble.domain.interactor.ShotLikeInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 05.08.2017.
 */
@PerActivity
class ShotDetailPresenter @Inject constructor(private val shotDetailInteractor: ShotDetailInteractor,
                                              private val shotLikeInteractor: ShotLikeInteractor)
    : ApiPresenter<List<Like>, ShotDetailContract.View>(), ShotDetailContract.Presenter {

    init {

    }

    override fun fetchLikes(shotId: String?) {
        this fetch shotLikeInteractor.getShotLikes(shotId!!)
    }

    override fun onRequestStart() {

    }

    override fun onRequestSuccess(data: List<Like>) {
        val likes: List<Like> = data
        Logger.log("Likes count is ${likes.count()}")
    }

    override fun onRequestError(errorMessage: String?) {

    }
}