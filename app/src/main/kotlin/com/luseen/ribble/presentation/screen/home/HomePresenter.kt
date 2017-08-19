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

    var state: NavigationState? = null

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreate() {
//        log {
//            "PRESENTER $state"
//        }
//        log {
//            "onCreate ${hashCode()}"
//        }
//        //view.openShotFragment()
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        view?.openShotFragment()
//        log {
//            "OnPResnterCreate ${hashCode()}"
//        }
    }

    override fun onPresenterDestroy() {
        super.onPresenterDestroy()
//        log {
//            "onPresenterDestroy ${hashCode()}"
//        }
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