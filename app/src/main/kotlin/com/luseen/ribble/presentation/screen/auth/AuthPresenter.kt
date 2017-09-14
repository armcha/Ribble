package com.luseen.ribble.presentation.screen.auth

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.net.Uri
import com.luseen.ribble.data.network.ApiConstants
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.User
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerActivity
class AuthPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<User, AuthContract.View>(), AuthContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        if (isRequestStarted)
            view?.showLoading()
    }

    override fun makeLogin() {
        view?.startOAuthIntent(Uri.parse(ApiConstants.LOGIN_OAUTH_URL))
    }

    override fun checkLogin(resultIntent: Intent?) {
        val userCode: String? = resultIntent?.data?.getQueryParameter("code")
        userCode?.let {
            this fetch userInteractor.getUser(userCode)
        }
    }

    override fun onRequestStart() {
        super.onRequestStart()
        view?.showLoading()
    }

    override fun onRequestSuccess(data: User) {
        super.onRequestSuccess(data)
        userInteractor.logIn()
        view?.hideLoading()
        view?.openHomeActivity()
    }

    override fun onRequestError(errorMessage: String?) {
        super.onRequestError(errorMessage)
        view?.hideLoading()
        view?.showError(errorMessage)
    }
}