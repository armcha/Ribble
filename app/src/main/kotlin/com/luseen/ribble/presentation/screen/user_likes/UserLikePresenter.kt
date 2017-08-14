package com.luseen.ribble.presentation.screen.user_likes

import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 13.08.2017.
 */
class UserLikePresenter @Inject constructor() : ApiPresenter<UserLikeContract.View>(), UserLikeContract.Presenter {

    override fun onRequestStart() {

    }

    override fun <T> onRequestSuccess(data: T) {

    }

    override fun onRequestError(errorMessage: String?) {

    }
}