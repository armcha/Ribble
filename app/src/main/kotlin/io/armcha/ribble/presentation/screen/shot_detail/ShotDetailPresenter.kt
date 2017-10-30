package io.armcha.ribble.presentation.screen.shot_detail

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.armcha.ribble.domain.fetcher.result_listener.RequestType
import io.armcha.ribble.domain.interactor.CommentInteractor
import io.armcha.ribble.domain.interactor.ShotLikeInteractor
import io.armcha.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 05.08.2017.
 */
class ShotDetailPresenter @Inject constructor(
        private val commentInteractor: CommentInteractor,
        private val shotLikeInteractor: ShotLikeInteractor)
    : ApiPresenter<ShotDetailContract.View>(), ShotDetailContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        when {
            COMMENTS statusIs LOADING || LIKE statusIs LOADING -> view?.showLoading()
            COMMENTS statusIs EMPTY_SUCCESS || COMMENTS statusIs ERROR -> view?.showNoComments()
            else -> fetchComments()
        }
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    fun onStop() {
        commentInteractor.deleteCommentsCache()
    }

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        fetchComments()
    }

    override fun handleShotLike(shotId: String?) {
        shotId?.let {
            complete(shotLikeInteractor.likeShot(shotId), LIKE) {
                TODO()
            }
        }
    }

    private fun fetchComments() {
        view?.getShotId()?.let {
            fetch(commentInteractor.getComments(it), COMMENTS) {
                view?.hideLoading()
                if (it.isNotEmpty())
                    view?.onDataReceive(it)
                else
                    view?.showNoComments()
            }
        }
    }

    override fun onRequestStart(requestType: RequestType) {
        super.onRequestStart(requestType)
        if (requestType == COMMENTS)
            view?.showLoading()
        else if (requestType == RequestType.LIKE) {

        }
    }

    override fun onRequestError(requestType: RequestType, errorMessage: String?) {
        super.onRequestError(requestType, errorMessage)
        if (requestType == COMMENTS) {
            view?.showNoComments()
        } else if (requestType == RequestType.LIKE) {

        }
        view?.hideLoading()
        view?.showError(errorMessage)

    }
}