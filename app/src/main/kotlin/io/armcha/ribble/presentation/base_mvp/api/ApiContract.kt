package io.armcha.ribble.presentation.base_mvp.api

import io.armcha.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 31.07.2017.
 */
interface ApiContract {

    interface View : BaseContract.View {

        fun showLoading() {}

        fun hideLoading() {}

        fun showError(message: String?) {}
    }

    interface Presenter<V : BaseContract.View> : BaseContract.Presenter<V>
}