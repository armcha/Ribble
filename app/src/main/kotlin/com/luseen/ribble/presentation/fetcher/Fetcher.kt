package com.luseen.ribble.presentation.fetcher

import com.luseen.ribble.presentation.base_mvp.result_listener.ResultListener
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 04.08.2017.
 */
@Singleton
class Fetcher @Inject constructor(private val disposable: CompositeDisposable) {

    private fun <T> getIOToMainTransformer(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> fetch(flowable: Flowable<T>, resultListener: ResultListener) {
        with(resultListener) {
            onRequestStart()
            disposable.add(flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onRequestSuccess(it)
                    }, {
                        onRequestError(it.message)
                    }))
        }
    }

    fun <T> fetch(observable: Observable<T>, resultListener: ResultListener) {
        with(resultListener) {
            onRequestStart()
            disposable.add(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onRequestSuccess(it)
                    }, {
                        onRequestError(it.message)
                    }))
        }
    }

    fun <T> fetch(single: Single<T>, resultListener: ResultListener) {
        with(resultListener) {
            onRequestStart()
            disposable.add(single
                    .compose(getIOToMainTransformer())
                    .subscribe({
                        onRequestSuccess(it)
                    }, {
                        onRequestError(it.message)
                    }))
        }
    }

    fun clear() {
        disposable.clear()
    }
}