package io.armcha.ribble.presentation.screen.user_following

import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.presentation.base_mvp.api.ApiContract

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