package com.luseen.ribble.presentation.screen.popular_shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.interactor.ShotListInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.utils.makeLog
import javax.inject.Inject

/**
 * Created by Chatikyan on 01.08.2017.
 */
@PerActivity
class PopularShotPresenter @Inject constructor(private val shotListInteractor: ShotListInteractor)
    : ApiPresenter<List<Shot>, PopularShotContract.View>(), PopularShotContract.Presenter {

    private var shotList: List<Shot> = listOf()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        isRequestStarted.makeLog()
        if (isRequestStarted)
            view?.showLoading()
        else
            view?.onShotListReceive(shotList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        this fetch shotListInteractor.getPopularShotList(100)
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
        }
    }

    override fun onRequestError(errorMessage: String?) {
        super.onRequestError(errorMessage)
        view?.showError(errorMessage)
        view?.hideLoading()
    }
}