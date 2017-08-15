package com.luseen.ribble.presentation.screen.auth

import android.content.Intent
import android.net.Uri
import com.luseen.ribble.data.network.ApiConstants
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.model.User
import com.luseen.ribble.utils.log
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerActivity
class AuthPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<AuthContract.View>(), AuthContract.Presenter {

    override fun makeLogin() {
        view?.startOAuthIntent(Uri.parse(ApiConstants.LOGIN_OAUTH_URL))
    }

    override fun checkLogin(resultIntent: Intent?) {
        val userCode: String? = resultIntent?.data?.getQueryParameter("code")
        userCode?.let {
            log { it }
            this fetch userInteractor.getUser(userCode)
        }
    }

    override fun onRequestStart() {
        log { "Start" }
    }

    override fun <T> onRequestSuccess(data: T) {
        val user = data as User
        userInteractor.saveUserLoggedIn()
        log {
            user.name
        }

        view?.openHomeActivity()
    }

    override fun onRequestError(errorMessage: String?) {
        log("onRequestError $errorMessage")
    }
}