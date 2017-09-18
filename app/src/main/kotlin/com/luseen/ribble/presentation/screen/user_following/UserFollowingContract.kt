package com.luseen.ribble.presentation.screen.user_following

import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 22.08.2017.
 */
interface UserFollowingContract {

    interface View : ApiContract.View {

        fun onShotListReceive(shotList: List<Shot>)

        fun showNoShots()
    }

    interface Presenter : ApiContract.Presenter<View>
}