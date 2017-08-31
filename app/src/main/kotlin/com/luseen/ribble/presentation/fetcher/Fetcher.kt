package com.luseen.ribble.presentation.fetcher

import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.base_mvp.result_listener.ResultListener
import com.luseen.ribble.utils.log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chatikyan on 04.08.2017.
 */
@PerActivity
class Fetcher @Inject constructor(private val disposable: CompositeDisposable) {

    private fun <T> getIOToMainTransformer(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun <T> Single<T>.receive(resultListener: ResultListener<T>): Disposable {
        return this.subscribe({
            resultListener.onRequestSuccess(it)
        }, {
            log { "onError ${it.message}" }
            resultListener.onRequestError(it.message)
        })
    }

    fun <T> fetch(flowable: Flowable<T>, resultListener: ResultListener<T>) {
        with(resultListener) {
            onRequestStart()
            disposable.add(flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onRequestSuccess(it)
                    }, {
                        log { "onError ${it.message}" }
                        onRequestError(it.message)
                    }))
        }
    }

    fun <T> fetch(observable: Observable<T>, resultListener: ResultListener<T>) {
        with(resultListener) {
            onRequestStart()
            disposable.add(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onRequestSuccess(it)
                    }, {
                        log { "onError ${it.message}" }
                        onRequestError(it.message)
                    }))
        }
    }

    fun <T> fetch(single: Single<T>, resultListener: ResultListener<T>) {
        with(resultListener) {
            onRequestStart()
            disposable.add(single
                    .compose(getIOToMainTransformer())
                    .receive(resultListener))
        }
    }

    fun clear() {
        disposable.clear()
    }
}