package com.luseen.ribble.presentation.base_mvp.api

import com.luseen.ribble.presentation.base_mvp.base.BaseContract
import com.luseen.ribble.presentation.base_mvp.base.BasePresenter
import com.luseen.ribble.presentation.base_mvp.result_listener.ResultListener
import com.luseen.ribble.presentation.fetcher.Fetcher
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Chatikyan on 04.08.2017.
 */
abstract class ApiPresenter<V : BaseContract.View> : BasePresenter<V>(), ResultListener {

    @Inject
    protected lateinit var fetcher: Fetcher

    fun <T> fetch(flowable: Flowable<T>) {
        fetcher.fetch(flowable, this)
    }

    fun <T> fetch(observable: Observable<T>) {
        fetcher.fetch(observable, this)
    }
}