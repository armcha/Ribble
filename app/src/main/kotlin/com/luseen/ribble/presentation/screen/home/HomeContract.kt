package com.luseen.ribble.presentation.screen.home

import android.support.v4.app.Fragment
import com.luseen.ribble.domain.entity.User
import com.luseen.ribble.presentation.base_mvp.base.BaseContract
import com.luseen.ribble.presentation.navigation.NavigationState

/**
 * Created by Chatikyan on 31.07.2017.
 */
interface HomeContract {

    interface View : BaseContract.View {

        fun openShotFragment()

        fun openLoginActivity()

        fun setArcArrowState()

        fun setArcHamburgerIconState()

        fun setToolBarTitle(title: String)

        fun updateDrawerInfo(user: User)

        fun checkNavigationItem(position: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun logOut()

        fun saveNavigatorState(state: NavigationState?)

        fun getNavigatorState(): NavigationState?

        fun handleFragmentChanges(fragment: Fragment)
    }
}