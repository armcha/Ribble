package com.luseen.ribble.presentation.screen.shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.logger.Logger
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.interactor.ShotListInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.model.Shot
import javax.inject.Inject

/**
 * Created by Chatikyan on 01.08.2017.
 */
@PerActivity
class ShotPresenter @Inject constructor(private val shotListInteractor: ShotListInteractor) : ApiPresenter<ShotContract.View>(), ShotContract.Presenter {

    private var shotList: MutableList<Shot> = arrayListOf()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        view?.onShotListReceive(shotList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(shotListInteractor.getShotList())
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    @SuppressWarnings("unchecked")
    override fun <T> onRequestSuccess(data: T) {
        val shotList: MutableList<Shot> = data as MutableList<Shot>
        this.shotList = shotList
        view?.onShotListReceive(shotList)
        view?.hideLoading()
    }

    override fun onRequestError(errorMessage: String?) {
        Logger.log("onError ${errorMessage}")
        view?.showError()
        view?.hideLoading()
    }
}