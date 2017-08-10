package com.luseen.ribble.presentation.fetcher

import com.luseen.ribble.presentation.base_mvp.result_listener.ResultListener
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Chatikyan on 04.08.2017.
 */
class Fetcher {

    fun <T> fetch(flowable: Flowable<T>, resultListener: ResultListener) {
        with(resultListener) {
            onRequestStart()
            flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onRequestSuccess(it)
                    }, {
                        onRequestError(it.message)
                    })
        }
    }

    fun <T> fetch(observable: Observable<T>, resultListener: ResultListener) {
        with(resultListener) {
            onRequestStart()
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onRequestSuccess(it)
                    }, {
                        onRequestError(it.message)
                    })
        }
    }
}