package io.armcha.ribble.presentation.base_mvp.base

import io.luseen.arch.BaseMVPContract

/**
 * Created by Chatikyan on 31.07.2017.
 */
interface BaseContract {

    interface View : io.luseen.arch.BaseMVPContract.View

    interface Presenter<V : io.luseen.arch.BaseMVPContract.View> : io.luseen.arch.BaseMVPContract.Presenter<V>
}