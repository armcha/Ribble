package com.luseen.ribble.presentation.screens.shot

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
class ShotPresenter @Inject constructor(protected val shotListInteractor: ShotListInteractor) : ApiPresenter<ShotContract.View>(), ShotContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fetch(shotListInteractor.getShotList())
    }

    override fun onStart() {
        Logger.log("onStart")
    }

    @SuppressWarnings("unchecked")
    override fun <T> onSuccess(data: T) {
        val shotList: List<Shot> = data as List<Shot>
        Logger.log("onSuccess ${shotList.size}")
    }

    override fun onError(errorMessage: String?) {
        Logger.log("onError ${errorMessage}")
    }
}