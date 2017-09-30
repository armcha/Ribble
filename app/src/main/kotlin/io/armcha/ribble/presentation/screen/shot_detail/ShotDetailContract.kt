package io.armcha.ribble.presentation.screen.shot_detail

import io.armcha.ribble.domain.entity.Comment
import io.armcha.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 05.08.2017.
 */
interface ShotDetailContract {

    interface View : ApiContract.View {

        fun onDataReceive(commentList: List<Comment>)

        fun getShotId(): String?

        fun showNoComments()
    }

    interface Presenter : ApiContract.Presenter<View> {

        fun handleShotLike(shotId: String?)
    }
}