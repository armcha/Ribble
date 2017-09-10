package com.luseen.ribble.presentation.screen.user_likes

import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 13.08.2017.
 */
interface UserLikeContract {

    interface View : ApiContract.View{

        fun onDataReceive(likeList: List<Like>)
    }

    interface Presenter : ApiContract.Presenter<View>
}