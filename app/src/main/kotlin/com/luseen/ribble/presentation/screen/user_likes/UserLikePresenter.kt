package com.luseen.ribble.presentation.screen.user_likes

import com.luseen.ribble.data.repository.UserDataRepository
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.model.Like
import com.luseen.ribble.utils.log
import javax.inject.Inject

/**
 * Created by Chatikyan on 13.08.2017.
 */
class UserLikePresenter @Inject constructor(private val userDataRepository: UserDataRepository)
    : ApiPresenter<UserLikeContract.View>(), UserLikeContract.Presenter {

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetch(userDataRepository.getUserLikes(count = 50))
    }

    override fun onRequestStart() {

    }

    override fun <T> onRequestSuccess(data: T) {
        val likeList = data as List<Like>
        if (likeList.isNotEmpty())
            view?.onDataReceive(likeList)

        likeList.forEach {
            log {
                it.shot?.title
            }
        }
    }

    override fun onRequestError(errorMessage: String?) {
        log {
            errorMessage
        }
    }
}