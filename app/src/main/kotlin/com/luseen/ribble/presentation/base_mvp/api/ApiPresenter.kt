package com.luseen.ribble.presentation.base_mvp.api

import android.support.annotation.CallSuper
import com.luseen.ribble.presentation.base_mvp.base.BaseContract
import com.luseen.ribble.presentation.base_mvp.base.BasePresenter
import com.luseen.ribble.presentation.fetcher.Fetcher
import com.luseen.ribble.presentation.fetcher.result_listener.RequestType
import com.luseen.ribble.presentation.fetcher.result_listener.ResultListener
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 04.08.2017.
 */

abstract class ApiPresenter<VIEW : BaseContract.View> : BasePresenter<VIEW>(), ResultListener {

    @Inject
    protected lateinit var fetcher: Fetcher
    private val TYPE_NONE = RequestType.TYPE_NONE
    protected val AUTH = RequestType.AUTH
    protected val POPULAR_SHOTS = RequestType.POPULAR_SHOTS
    protected val RECENT_SHOTS = RequestType.RECENT_SHOTS
    protected val FOLLOWINGS_SHOTS = RequestType.FOLLOWINGS_SHOTS
    protected val LIKED_SHOTS = RequestType.LIKED_SHOTS
    protected val COMMENTS = RequestType.COMMENTS
    protected val LIKE = RequestType.LIKE

    protected fun requestStatus(requestType: RequestType) = fetcher.getRequestStatus(requestType)

    fun <TYPE> fetch(flowable: Flowable<TYPE>,
                     requestType: RequestType = TYPE_NONE, success: (TYPE) -> Unit) {
        fetcher.fetch(flowable, requestType, this, success)
    }

    fun <TYPE> fetch(observable: Observable<TYPE>,
                     requestType: RequestType = TYPE_NONE, success: (TYPE) -> Unit) {
        fetcher.fetch(observable, requestType, this, success)
    }

    fun <TYPE> fetch(single: Single<TYPE>,
                     requestType: RequestType = TYPE_NONE, success: (TYPE) -> Unit) {
        fetcher.fetch(single, requestType, this, success)
    }

    fun complete(completable: Completable,
                 requestType: RequestType = TYPE_NONE, success: () -> Unit = {}) {
        fetcher.complete(completable, requestType, this, success)
    }

    @CallSuper
    override fun onPresenterDestroy() {
        super.onPresenterDestroy()
        fetcher.clear()
    }

    @CallSuper
    override fun onRequestStart(requestType: RequestType) {
        onRequestStart()
    }

    @CallSuper
    override fun onRequestError(requestType: RequestType, errorMessage: String?) {
        onRequestError(errorMessage)
    }
}