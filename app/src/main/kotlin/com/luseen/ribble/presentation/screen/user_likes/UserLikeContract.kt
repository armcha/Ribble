package com.luseen.ribble.presentation.screen.user_likes

import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 13.08.2017.
 */
interface UserLikeContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}