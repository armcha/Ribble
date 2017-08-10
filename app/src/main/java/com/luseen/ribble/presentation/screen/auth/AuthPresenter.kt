package com.luseen.ribble.presentation.screen.auth

import com.luseen.ribble.di.scope.PerUser
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.utils.log
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerUser
class AuthPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<AuthContract.View>(), AuthContract.Presenter {

    override fun makeLogin() {
        fetch(userInteractor.getToken("fa26ba58529ff7094a8250f8ce4a89c36dd6a6cfcc382c81bcb2b4a8e9f936b2"))
    }

    override fun onRequestStart() {
        log("Start")
    }

    override fun <T> onRequestSuccess(data: T) {
        log(data as Any)
    }

    override fun onRequestError(errorMessage: String?) {
        log("onRequestError $errorMessage")
    }
}