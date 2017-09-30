package io.armcha.ribble.presentation.screen.shot

import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 01.08.2017.
 */
interface ShotContract {

    interface View : ApiContract.View {

        fun onShotListReceive(shotList: List<Shot>)

        fun getShotType(): String

        fun showNoShots()
    }

    interface Presenter : ApiContract.Presenter<View>
}