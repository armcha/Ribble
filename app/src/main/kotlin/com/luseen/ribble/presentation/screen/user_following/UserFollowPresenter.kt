package com.luseen.ribble.presentation.screen.user_following

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
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
        view?.onShotListReceive(shotList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        this fetch userInteractor.getFollowing(100)
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    override fun onRequestSuccess(data: List<Shot>) {
        this.shotList = data
        if (shotList.isNotEmpty()) {
            view?.onShotListReceive(shotList)
            view?.hideLoading()
        }
    }

    override fun onRequestError(errorMessage: String?) {
        view?.hideLoading()
        view?.showError()
    }
}