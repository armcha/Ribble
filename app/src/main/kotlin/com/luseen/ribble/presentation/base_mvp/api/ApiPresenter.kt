package com.luseen.ribble.presentation.base_mvp.api

import android.support.annotation.CallSuper
import com.luseen.ribble.presentation.base_mvp.base.BaseContract
import com.luseen.ribble.presentation.base_mvp.base.BasePresenter
import com.luseen.ribble.presentation.base_mvp.result_listener.ResultListener
import com.luseen.ribble.presentation.fetcher.Fetcher
import com.luseen.ribble.utils.log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 04.08.2017.
 */
abstract class ApiPresenter<TYPE, VIEW : BaseContract.View> : BasePresenter<VIEW>(), ResultListener<TYPE> {

    @Inject
    protected lateinit var fetcher: Fetcher
    protected var isRequestStarted = false

    infix fun fetch(flowable: Flowable<TYPE>) {
        fetcher.fetch(flowable, this)
    }

    infix fun fetch(observable: Observable<TYPE>) {
        fetcher.fetch(observable, this)
    }

    infix fun fetch(single: Single<TYPE>) {
        fetcher.fetch(single, this)
    }

    @CallSuper
    override fun onPresenterDestroy() {
        super.onPresenterDestroy()
        fetcher.clear()
    }

    @CallSuper
    override fun onRequestStart() {
        isRequestStarted = true
        log {
            "onRequestStart $isRequestStarted"
        }
    }

    @CallSuper
    override fun onRequestSuccess(data: TYPE) {
        isRequestStarted = false
        log {
            "onRequestSuccess $isRequestStarted"
        }
    }

    @CallSuper
    override fun onRequestError(errorMessage: String?) {
        isRequestStarted = false
        log {
            "onRequestError $isRequestStarted"
        }
    }
}