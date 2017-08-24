package com.luseen.ribble.presentation.screen.home

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.base.BasePresenter
import com.luseen.ribble.presentation.navigation.NavigationState
import javax.inject.Inject

/**
 * Created by Chatikyan on 31.07.2017.
 */
@PerActivity
class HomePresenter @Inject constructor(private val userInteractor: UserInteractor) : BasePresenter<HomeContract.View>(),
        HomeContract.Presenter {

    private var state: NavigationState? = null
    private var isDrawerLocked = false

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (isDrawerLocked) {
            view?.onDrawerLocked()
        } else {
            view?.onDrawerUnlocked()
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        view?.openShotFragment()
    }

    override fun handleDrawerLock() {
        isDrawerLocked = true
        view?.onDrawerLocked()
    }

    override fun handleDrawerUnLock() {
        isDrawerLocked = false
        view?.onDrawerUnlocked()
    }

    override fun logOut() {
        userInteractor.saveUserLoggedOut()
        view?.openLoginActivity()
    }

    override fun saveNavigatorState(state: NavigationState?) {
        this.state = state
    }

    override fun getNavigatorState(): NavigationState? {
        return state
    }

}