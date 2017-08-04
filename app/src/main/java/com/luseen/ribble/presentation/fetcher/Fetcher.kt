package com.luseen.ribble.presentation.fetcher

import com.luseen.ribble.presentation.base_mvp.result_listener.ResultListener
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 04.08.2017.
 */
@Singleton
class Fetcher @Inject constructor(){

    fun <T> fetch(flowable: Flowable<T>, resultListener: ResultListener) {
        with(resultListener) {
            onStart()
            flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onSuccess(it)
                    }, {
                        onError(it.message)
                    })
        }
    }

    fun <T> fetch(observable: Observable<T>, resultListener: ResultListener) {
        with(resultListener) {
            onStart()
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onSuccess(it)
                    }, {
                        onError(it.message)
                    })
        }
    }
}