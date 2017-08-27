package com.luseen.ribble.presentation.screen.shot_detail

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.domain.interactor.CommentInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.utils.log
import javax.inject.Inject

/**
 * Created by Chatikyan on 05.08.2017.
 */
class ShotDetailPresenter @Inject constructor(private val commentInteractor: CommentInteractor)
    : ApiPresenter<List<Comment>, ShotDetailContract.View>(), ShotDetailContract.Presenter {

    private var commentList = listOf<Comment>()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        view?.onDataReceive(commentList)
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        val shotId = view?.getShotId()
        if (shotId != null)
            this fetch commentInteractor.getComments(shotId)
    }

    override fun onRequestStart() {

    }

    override fun onRequestSuccess(data: List<Comment>) {
        commentList = data
        view?.onDataReceive(data)
    }

    override fun onRequestError(errorMessage: String?) {
        log {
            "onRequestError ${errorMessage}"
        }
    }
}