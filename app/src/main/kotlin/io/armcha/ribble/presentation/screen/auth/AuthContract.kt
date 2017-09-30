package io.armcha.ribble.presentation.screen.auth

import android.content.Intent
import android.net.Uri
import io.armcha.ribble.presentation.base_mvp.api.ApiContract

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface AuthContract {

    interface View : ApiContract.View {

        fun startOAuthIntent(uri: Uri)

        fun openHomeActivity()
    }

    interface Presenter : ApiContract.Presenter<View> {

        fun makeLogin()

        fun checkLogin(resultIntent: Intent?)
    }
}