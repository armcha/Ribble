package com.luseen.ribble.presentation.screen.home

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.User
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.navigation.NavigationState
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId
import com.luseen.ribble.utils.emptyString
import javax.inject.Inject

/**
 * Created by Chatikyan on 31.07.2017.
 */
@PerActivity
class HomePresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<User,HomeContract.View>(), HomeContract.Presenter {

    private var state: NavigationState? = null
    private var isDrawerLocked = false
    private var activeTitle = emptyString()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (isDrawerLocked) {
            view?.onDrawerLocked()
            activeTitle = NavigationId.SHOT_DETAIL.name
        } else {
            view?.onDrawerUnlocked()
        }
        view?.setToolBarTitle(activeTitle)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        this fetch userInteractor.getAuthenticatedUser()
        view?.openShotFragment()
    }

    override fun onRequestStart() {
    }

    override fun onRequestSuccess(data: User) {
        view?.updateDrawerInfo(data)
    }

    override fun onRequestError(errorMessage: String?) {
    }

    override fun handleDrawerLock() {
        isDrawerLocked = true
        view?.onDrawerLocked()
        view?.setToolBarTitle(NavigationId.SHOT_DETAIL.name)
    }

    override fun handleDrawerUnLock() {
        isDrawerLocked = false
        view?.onDrawerUnlocked()
        view?.setToolBarTitle(NavigationId.SHOT.name)
    }

    override fun handleTitleChanges(newTitle: String) {
        view?.setToolBarTitle(newTitle)
        activeTitle = newTitle
    }

    override fun logOut() {
        userInteractor.logOut()
        view?.openLoginActivity()
    }

    override fun saveNavigatorState(state: NavigationState?) {
        this.state = state
    }

    override fun getNavigatorState(): NavigationState? {
        return state
    }
}