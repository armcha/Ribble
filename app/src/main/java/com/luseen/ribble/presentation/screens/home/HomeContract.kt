package com.luseen.ribble.presentation.screens.home

import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 31.07.2017.
 */
interface HomeContract {

    interface View : BaseContract.View {

        fun openShotFragment()
    }

    interface Presenter : BaseContract.Presenter<View> {


    }
}