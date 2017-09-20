package com.luseen.ribble.presentation.screen.user_following

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.base_mvp.api.Status
import javax.inject.Inject

/**
 * Created by Chatikyan on 22.08.2017.
 */
@PerActivity
class UserFollowPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<List<Shot>, UserFollowingContract.View>(), UserFollowingContract.Presenter {

    private var shotList: List<Shot> = arrayListOf()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        when (status) {
            Status.LOADING -> view?.showLoading()
            Status.EMPTY,Status.ERROR -> view?.showNoShots()
            else -> view?.onShotListReceive(shotList)
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        this fetch userInteractor.getFollowing(100)
    }

    override fun onRequestStart() {
        super.onRequestStart()
        view?.showLoading()
    }

    override fun onRequestSuccess(data: List<Shot>) {
        super.onRequestSuccess(data)
        this.shotList = data
        view?.hideLoading()
        if (shotList.isNotEmpty()) {
            view?.onShotListReceive(shotList)
        } else {
            view?.showNoShots()
        }
    }

    override fun onRequestError(errorMessage: String?) {
        super.onRequestError(errorMessage)
        view?.showNoShots()
        view?.hideLoading()
        view?.showError(errorMessage)
    }
}