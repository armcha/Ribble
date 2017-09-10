package com.luseen.ribble.presentation.screen.shot_detail

import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 05.08.2017.
 */
interface ShotDetailContract {

    interface View : ApiContract.View{

        fun onDataReceive(commentList: List<Comment>)

        fun getShotId():String?

        fun showNoComments()
    }

    interface Presenter : ApiContract.Presenter<View>
}