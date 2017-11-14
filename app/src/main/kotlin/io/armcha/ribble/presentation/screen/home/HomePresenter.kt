package io.armcha.ribble.presentation.screen.home

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.Fragment
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.User
import io.armcha.ribble.domain.interactor.UserInteractor
import io.armcha.ribble.presentation.base_mvp.api.ApiPresenter
import io.armcha.ribble.presentation.base_mvp.base.BaseFragment
import io.armcha.ribble.presentation.navigation.NavigationState
import io.armcha.ribble.presentation.utils.extensions.emptyString
import io.armcha.ribble.presentation.widget.navigation_view.NavigationId
import javax.inject.Inject

/**
 * Created by Chatikyan on 31.07.2017.
 */
@PerActivity
class HomePresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<HomeContract.View>(), HomeContract.Presenter {

    private var isArcIcon = false
    private var user: User? = null
    private var isDrawerOpened = false
    private var activeTitle = emptyString
    private var state: NavigationState? = null
    private var currentNavigationSelectedItem = 0

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (isArcIcon || isDrawerOpened) {
            view?.setArcArrowState()
        } else {
            view?.setArcHamburgerIconState()
        }
        view?.setToolBarTitle(activeTitle)
        user?.let {
            view?.updateDrawerInfo(it)
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userInteractor.getAuthenticatedUser()) {
            user = it
            view?.updateDrawerInfo(it)
        }
        view?.openShotFragment()
    }

    override fun onPresenterDestroy() {
        super.onPresenterDestroy()
        userInteractor.clearCache()
    }

    override fun handleFragmentChanges(currentTag: String, fragment: Fragment) {
        val tag = if (fragment is BaseFragment<*, *>) {
            fragment.getTitle()
        } else {
            emptyString
        }

        view?.setToolBarTitle(tag)
        activeTitle = tag
        if (currentTag == NavigationId.SHOT_DETAIL.fullName) {
            isArcIcon = true
            view?.setArcArrowState()
        } else if (isArcIcon) {
            isArcIcon = false
            view?.setArcHamburgerIconState()
        }

        val checkPosition = when (tag) {
            NavigationId.SHOT.name -> 0
            NavigationId.USER_LIKES.name -> 1
            NavigationId.FOLLOWING.name -> 2
            NavigationId.ABOUT.name -> 3
            else -> currentNavigationSelectedItem
        }

        if (currentNavigationSelectedItem != checkPosition) {
            currentNavigationSelectedItem = checkPosition
            view?.checkNavigationItem(currentNavigationSelectedItem)
        }
    }

    override fun handleDrawerOpen() {
        if (!isArcIcon)
            view?.setArcArrowState()
        isDrawerOpened = true
    }

    override fun handleDrawerClose() {
        if (!isArcIcon && isDrawerOpened)
            view?.setArcHamburgerIconState()
        isDrawerOpened = false
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