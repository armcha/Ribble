package com.luseen.ribble.presentation.screen.user_likes

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.data.repository.UserDataRepository
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.utils.log
import javax.inject.Inject

/**
 * Created by Chatikyan on 13.08.2017.
 */
class UserLikePresenter @Inject constructor(private val userDataRepository: UserDataRepository)
    : ApiPresenter<UserLikeContract.View>(), UserLikeContract.Presenter {

    private var likeList: List<Like> = emptyList()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        view?.onDataReceive(likeList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userDataRepository.getUserLikes(count = 50))
    }

    override fun onRequestStart() {

    }

    override fun <T> onRequestSuccess(data: T) {
        likeList = data as List<Like>
        if (likeList.isNotEmpty())
            view?.onDataReceive(likeList)
    }

    override fun onRequestError(errorMessage: String?) {
        log {
            errorMessage
        }
    }
}