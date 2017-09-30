package io.armcha.ribble.presentation.screen.user_likes

import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 13.08.2017.
 */
interface UserLikeContract {

    interface View : ApiContract.View{

        fun onDataReceive(likeList: List<Like>)

        fun showNoShots()
    }

    interface Presenter : ApiContract.Presenter<View>
}