package io.armcha.ribble.presentation.base_mvp.base

import io.armcha.arch.BaseMVPContract

/**
 * Created by Chatikyan on 31.07.2017.
 */
interface BaseContract {

    interface View : BaseMVPContract.View

    interface Presenter<V : BaseMVPContract.View> : BaseMVPContract.Presenter<V>
}