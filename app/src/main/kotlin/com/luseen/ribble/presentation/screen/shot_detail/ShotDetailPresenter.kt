package com.luseen.ribble.presentation.screen.shot_detail

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.domain.interactor.CommentInteractor
import com.luseen.ribble.domain.interactor.ShotLikeInteractor
import com.luseen.ribble.presentation.base_mvp.api.ApiPresenter
import com.luseen.ribble.presentation.fetcher.Status
import com.luseen.ribble.presentation.fetcher.result_listener.RequestType
import javax.inject.Inject

/**
 * Created by Chatikyan on 05.08.2017.
 */
class ShotDetailPresenter @Inject constructor(
        private val commentInteractor: CommentInteractor,
        private val shotLikeInteractor: ShotLikeInteractor)
    : ApiPresenter<ShotDetailContract.View>(), ShotDetailContract.Presenter {

    private var commentList = listOf<Comment>()

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        val commentStatus = requestStatus(RequestType.COMMENTS)
        when (commentStatus) {
            Status.LOADING -> view?.showLoading()
            Status.EMPTY, Status.ERROR -> view?.showNoComments()
            else -> view?.onDataReceive(commentList)
        }
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        view?.getShotId()?.apply {
            fetchComments(this)
        }
    }

    override fun handleShotLike(shotId: String?) {
        shotId?.let {
            complete(shotLikeInteractor.likeShot(shotId), RequestType.LIKE) {

            }
        }
    }

    private fun fetchComments(shotId: String) {
        fetch(commentInteractor.getComments(shotId), RequestType.COMMENTS) {
            commentList = it
            with(view) {
                hideLoading()
                if (it.isNotEmpty())
                    onDataReceive(it)
                else
                    showNoComments()
            }
        }
    }

    override fun onRequestStart(requestType: RequestType) {
        super.onRequestStart(requestType)
        if (requestType == RequestType.COMMENTS)
            view?.showLoading()
        else if (requestType == RequestType.LIKE) {

        }
    }

    override fun onRequestError(requestType: RequestType, errorMessage: String?) {
        super.onRequestError(requestType, errorMessage)
        if (requestType == RequestType.COMMENTS) {
            view?.showNoComments()
        } else if (requestType == RequestType.LIKE) {

        }
        view?.hideLoading()
        view?.showError(errorMessage)

    }
}