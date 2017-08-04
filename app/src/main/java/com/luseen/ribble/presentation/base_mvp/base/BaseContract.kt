package com.luseen.ribble.presentation.base_mvp.base

import com.luseen.arch.BaseMVPContract

/**
 * Created by Chatikyan on 31.07.2017.
 */
interface BaseContract {

    interface View : BaseMVPContract.View

    interface Presenter<V : View> : BaseMVPContract.Presenter<V>
}