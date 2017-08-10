package com.luseen.ribble.presentation.base_mvp.base

import com.luseen.arch.BaseMVPPresenter

/**
 * Created by Chatikyan on 31.07.2017.
 */
abstract class BasePresenter<V : BaseContract.View> : BaseMVPPresenter<V>() {

}