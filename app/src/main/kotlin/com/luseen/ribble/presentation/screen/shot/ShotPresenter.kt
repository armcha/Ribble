package com.luseen.ribble.presentation.screen.shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.interactor.ShotListInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.fetcher.Status
import javax.inject.Inject

/**
 * Created by Chatikyan on 01.08.2017.
 */
class ShotPresenter @Inject constructor(private val shotListInteractor: ShotListInteractor)
    : ApiPresenter<ShotContract.View>(), ShotContract.Presenter {

    private var shotList: List<Shot> = listOf()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        val requestStatus = if (view?.getShotType() == TYPE_POPULAR)
            requestStatus(POPULAR_SHOTS)
        else
            requestStatus(RECENT_SHOTS)

        when (requestStatus) {
            Status.LOADING -> view?.showLoading()
            Status.EMPTY, Status.ERROR -> view?.showNoShots()
            else -> view?.onShotListReceive(shotList)
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()

        val success = { it: List<Shot> ->
            this.shotList = it
            view?.hideLoading()
            if (it.isNotEmpty()) {
                view?.onShotListReceive(it) ?: Unit
            } else {
                view?.showNoShots() ?: Unit
            }
        }

        if (view?.getShotType() == TYPE_POPULAR)
            fetch(shotListInteractor.getPopularShotList(100), POPULAR_SHOTS, success)
        else
            fetch(shotListInteractor.getRecentShotList(100), RECENT_SHOTS, success)
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    override fun onRequestError(errorMessage: String?) {
        if (view?.getShotType() == TYPE_POPULAR)
            view?.showError(errorMessage)
        view?.showNoShots()
        view?.hideLoading()
    }
}