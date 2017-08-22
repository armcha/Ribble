package com.luseen.ribble.presentation.screen.user_following

import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 22.08.2017.
 */
interface UserFollowingContract {

    interface View : BaseContract.View {

        fun showLoading()

        fun hideLoading()

        fun showError()

        fun onShotListReceive(shotList: List<Shot>)
    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}