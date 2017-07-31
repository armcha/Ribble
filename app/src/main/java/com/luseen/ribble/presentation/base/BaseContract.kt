package com.luseen.ribble.presentation.base

import com.luseen.arch.BaseMVPContract

/**
 * Created by Chatikyan on 31.07.2017.
 */
interface BaseContract {

    interface View : BaseMVPContract.View

    interface Presenter<V : BaseContract.View> : BaseMVPContract.Presenter<V>
}