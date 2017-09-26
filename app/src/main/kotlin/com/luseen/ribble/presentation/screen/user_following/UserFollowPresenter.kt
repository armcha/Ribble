package com.luseen.ribble.presentation.screen.user_following

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.fetcher.Status
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 22.08.2017.
 */
@PerActivity
class UserFollowPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<UserFollowingContract.View>(), UserFollowingContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        val requestStatus = requestStatus(FOLLOWINGS_SHOTS)
        when (requestStatus) {
            Status.LOADING -> view?.showLoading()
            Status.EMPTY_SUCCESS, Status.ERROR -> view?.showNoShots()
            else -> view?.onShotListReceive(userInteractor.getFollowingFromMemory())
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userInteractor.getFollowing(100), FOLLOWINGS_SHOTS) {
            view?.hideLoading()
            if (it.isNotEmpty()) {
                view?.onShotListReceive(it)
            } else {
                view?.showNoShots()
            }
        }
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    override fun onRequestError(errorMessage: String?) {
        view?.showNoShots()
        view?.hideLoading()
        view?.showError(errorMessage)
    }
}