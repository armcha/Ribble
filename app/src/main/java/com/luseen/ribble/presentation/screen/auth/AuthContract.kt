package com.luseen.ribble.presentation.screen.auth

import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface AuthContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun makeLogin()
    }
}