package com.luseen.ribble.presentation.screen.shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.interactor.ShotListInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.base_mvp.api.Status
import javax.inject.Inject

/**
 * Created by Chatikyan on 01.08.2017.
 */
class ShotPresenter @Inject constructor(private val shotListInteractor: ShotListInteractor)
    : ApiPresenter<List<Shot>, ShotContract.View>(), ShotContract.Presenter {

    private var shotList: List<Shot> = listOf()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        when (status) {
            Status.LOADING -> view?.showLoading()
            Status.EMPTY -> view?.showNoShots()
            else -> view?.onShotListReceive(shotList)
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        if (view?.getShotType() == TYPE_POPULAR)
            this fetch shotListInteractor.getPopularShotList(100)
        else
            this fetch shotListInteractor.getRecentShotList(100)
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
        view?.showError(errorMessage)
        view?.hideLoading()
    }
}