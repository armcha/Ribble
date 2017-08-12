package com.luseen.ribble.presentation.screen.dispatch

import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 11.08.2017.
 */
interface DispatchContract {

    interface View : BaseContract.View {

        fun openHomeActivity()

        fun openLoginActivity()
    }

    interface Presenter : BaseContract.Presenter<View>
}