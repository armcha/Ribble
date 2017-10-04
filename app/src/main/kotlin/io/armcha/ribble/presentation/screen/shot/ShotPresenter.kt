package io.armcha.ribble.presentation.screen.shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.interactor.ShotListInteractor
import io.armcha.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 01.08.2017.
 */
class ShotPresenter @Inject constructor(private val shotListInteractor: ShotListInteractor)
    : ApiPresenter<ShotContract.View>(), ShotContract.Presenter {

    private val isPopularType = view?.getShotType() == TYPE_POPULAR

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        val requestType = if (isPopularType) POPULAR_SHOTS else RECENT_SHOTS

        when (requestType.status) {
            LOADING -> view?.showLoading()
            EMPTY_SUCCESS, ERROR -> view?.showNoShots()
            else -> {
                view?.onShotListReceive(if (isPopularType)
                    shotListInteractor.popularShotFromMemory()
                else
                    shotListInteractor.recentShotFromMemory())
            }
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()

        val success = { it: List<Shot> ->
            view?.hideLoading()
            if (it.isNotEmpty()) {
                view?.onShotListReceive(it) ?: Unit
            } else {
                view?.showNoShots() ?: Unit
            }
        }

        if (isPopularType)
            fetch(shotListInteractor.popularShotList(100), POPULAR_SHOTS, success)
        else
            fetch(shotListInteractor.recentShotList(100), RECENT_SHOTS, success)
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