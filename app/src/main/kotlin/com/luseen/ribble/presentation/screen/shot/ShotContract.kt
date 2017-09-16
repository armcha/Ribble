package com.luseen.ribble.presentation.screen.shot

import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 01.08.2017.
 */
interface ShotContract {

    interface View : ApiContract.View {

        fun onShotListReceive(shotList: List<Shot>)

        fun getShotType(): String
    }

    interface Presenter : ApiContract.Presenter<View>
}