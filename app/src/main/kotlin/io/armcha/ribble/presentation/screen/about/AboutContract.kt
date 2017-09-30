package io.armcha.ribble.presentation.screen.about

import io.armcha.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 01.08.2017.
 */
interface AboutContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}