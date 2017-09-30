package io.armcha.ribble.presentation.screen.dispatch

import io.armcha.ribble.presentation.base_mvp.base.BaseContract

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