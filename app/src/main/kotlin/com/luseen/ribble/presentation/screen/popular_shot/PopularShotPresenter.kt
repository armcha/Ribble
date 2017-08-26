package com.luseen.ribble.presentation.screen.popular_shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
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
    : ApiPresenter<List<Shot>,PopularShotContract.View>(), PopularShotContract.Presenter {

    private var shotList: List<Shot> = arrayListOf()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        view?.onShotListReceive(shotList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        this fetch shotListInteractor.getPopularShotList(100)
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    override fun  onRequestSuccess(data: List<Shot>) {
        this.shotList = data
        if (shotList.isNotEmpty()) {
            view?.onShotListReceive(shotList)
            view?.hideLoading()
        }
    }

    override fun onRequestError(errorMessage: String?) {
        view?.showError()
        view?.hideLoading()
    }
}