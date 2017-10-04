package io.armcha.ribble.presentation.screen.user_following

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.interactor.UserInteractor
import io.armcha.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 22.08.2017.
 */
@PerActivity
class UserFollowPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<UserFollowingContract.View>(), UserFollowingContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        when (FOLLOWINGS_SHOTS.status) {
            LOADING -> view?.showLoading()
            EMPTY_SUCCESS, ERROR -> view?.showNoShots()
            else -> view?.onShotListReceive(userInteractor.getFollowingFromMemory())
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userInteractor.getFollowing(100), FOLLOWINGS_SHOTS, success)
    }

    private val success: (shotList: List<Shot>) -> Unit = {
        view?.hideLoading()
        if (it.isNotEmpty()) {
            view?.onShotListReceive(it)
        } else {
            view?.showNoShots()
        }
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    override fun onRequestError(errorMessage: String?) {
        view?.onRequestError(errorMessage)
    }

    private fun UserFollowingContract.View.onRequestError(errorMessage: String?) {
        showNoShots()
        hideLoading()
        showError(errorMessage)
    }
}