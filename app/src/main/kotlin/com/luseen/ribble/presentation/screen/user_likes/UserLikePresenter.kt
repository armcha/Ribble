package com.luseen.ribble.presentation.screen.user_likes

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.utils.log
import javax.inject.Inject

/**
 * Created by Chatikyan on 13.08.2017.
 */
class UserLikePresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<List<Like>, UserLikeContract.View>(), UserLikeContract.Presenter {

    private var likeList: List<Like> = emptyList()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        view?.onDataReceive(likeList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userInteractor.getUserLikes(count = 50))
    }

    override fun onRequestStart() {

    }

    override fun onRequestSuccess(data: List<Like>) {
        likeList = data
        if (likeList.isNotEmpty())
            view?.onDataReceive(likeList)
    }

    override fun onRequestError(errorMessage: String?) {
        log {
            errorMessage
        }
    }
}