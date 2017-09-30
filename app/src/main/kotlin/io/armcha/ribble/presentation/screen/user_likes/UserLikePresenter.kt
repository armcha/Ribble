package io.armcha.ribble.presentation.screen.user_likes

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.armcha.ribble.domain.fetcher.Status
import io.armcha.ribble.domain.interactor.UserInteractor
import io.armcha.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 13.08.2017.
 */
class UserLikePresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<UserLikeContract.View>(), UserLikeContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        when (requestStatus(LIKED_SHOTS)) {
            Status.LOADING -> view?.showLoading()
            Status.EMPTY_SUCCESS, Status.ERROR -> view?.showNoShots()
            else -> view?.onDataReceive(userInteractor.getUserLikesFromMemory())
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userInteractor.getUserLikes(count = 100), LIKED_SHOTS) {
            view?.hideLoading()
            if (it.isNotEmpty())
                view?.onDataReceive(it)
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