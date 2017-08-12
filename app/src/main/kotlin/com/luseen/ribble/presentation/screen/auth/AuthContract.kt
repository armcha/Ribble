package com.luseen.ribble.presentation.screen.auth

import android.content.Intent
import android.net.Uri
import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface AuthContract {

    interface View : BaseContract.View {

        fun startOAuthIntent(uri: Uri)

        fun openHomeActivity()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun makeLogin()

        fun checkLogin(resultIntent: Intent?)
    }
}