package com.luseen.ribble.presentation.screen.shot_detail

import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 05.08.2017.
 */
interface ShotDetailContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View> {

        fun fetchLikes(shotId: String?)
    }
}