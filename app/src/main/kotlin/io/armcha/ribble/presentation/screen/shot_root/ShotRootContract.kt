package io.armcha.ribble.presentation.screen.shot

import io.armcha.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 01.08.2017.
 */
interface ShotRootContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}