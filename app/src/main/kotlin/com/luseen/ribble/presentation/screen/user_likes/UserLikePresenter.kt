package com.luseen.ribble.presentation.screen.user_likes

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.fetcher.Status
import javax.inject.Inject

/**
 * Created by Chatikyan on 13.08.2017.
 */
class UserLikePresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<UserLikeContract.View>(), UserLikeContract.Presenter {

    private var likeList: List<Like> = emptyList()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        when (requestStatus(LIKED_SHOTS)) {
            Status.LOADING -> view?.showLoading()
            Status.EMPTY, Status.ERROR -> view?.showNoShots()
            else -> view?.onDataReceive(likeList)
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userInteractor.getUserLikes(count = 100), LIKED_SHOTS) {
            likeList = it
            view?.hideLoading()
            if (likeList.isNotEmpty())
                view?.onDataReceive(likeList)
            else
                view?.showNoShots()
        }
    }

    override fun onRequestStart() {
        super.onRequestStart()
        view?.showLoading()
    }

    override fun onRequestError(errorMessage: String?) {
        super.onRequestError(errorMessage)
        view?.showNoShots()
        view?.hideLoading()
        view?.showError(errorMessage)
    }
}