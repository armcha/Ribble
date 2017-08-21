package com.luseen.ribble.presentation.screen.popular_shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.logger.Logger
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.interactor.ShotListInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 01.08.2017.
 */
@PerActivity
class PopularShotPresenter @Inject constructor(private val shotListInteractor: ShotListInteractor)
    : ApiPresenter<PopularShotContract.View>(), PopularShotContract.Presenter {

    private var shotList: MutableList<Shot> = arrayListOf()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        view?.onShotListReceive(shotList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        this fetch shotListInteractor.getRecentShotList(10)
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    @SuppressWarnings("unchecked")
    override fun <T> onRequestSuccess(data: T) {
        val shotList: MutableList<Shot> = data as MutableList<Shot>
        this.shotList = shotList
        if (shotList.isNotEmpty()) {
            view?.onShotListReceive(shotList)
            view?.hideLoading()
        }
    }

    override fun onRequestError(errorMessage: String?) {
        Logger.log("onError $errorMessage")
        view?.showError()
        view?.hideLoading()
    }
}