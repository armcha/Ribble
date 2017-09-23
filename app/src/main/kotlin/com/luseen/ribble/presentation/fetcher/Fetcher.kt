package com.luseen.ribble.presentation.fetcher

import com.luseen.ribble.presentation.fetcher.result_listener.RequestType
import com.luseen.ribble.presentation.fetcher.result_listener.ResultListener
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 04.08.2017.
 */
@Singleton
class Fetcher @Inject constructor(private val disposable: CompositeDisposable) {

    val requestMap = ConcurrentHashMap<RequestType, Status>()

    private fun <T> getIOToMainTransformer(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> fetch(flowable: Flowable<T>, requestType: RequestType,
                  resultListener: ResultListener, success: (T) -> Unit) {
        with(resultListener) {
            this startAndAdd requestType
            disposable.add(flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(function<T>(requestType, success),
                            function(requestType, resultListener)))
        }
    }

    fun <T> fetch(observable: Observable<T>, requestType: RequestType,
                  resultListener: ResultListener, success: (T) -> Unit) {
        with(resultListener) {
            this startAndAdd requestType
            disposable.add(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(function<T>(requestType, success),
                            function(requestType, resultListener)))
        }
    }

    fun <T> fetch(single: Single<T>, requestType: RequestType,
                  resultListener: ResultListener, success: (T) -> Unit) {
        with(resultListener) {
            this startAndAdd requestType
            disposable.add(single
                    .compose(getIOToMainTransformer())
                    .subscribe(function<T>(requestType, success),
                            function(requestType, resultListener)))
        }
    }

    fun <T> fetch(completable: Completable, requestType: RequestType,
                  resultListener: ResultListener, success: (T) -> Unit) {
        with(resultListener) {
            this startAndAdd requestType
            disposable.add(completable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ function(requestType, success) },
                            function(requestType, resultListener)))
        }
    }

    private infix fun ResultListener.startAndAdd(requestType: RequestType) {
        onRequestStart(requestType)
        if (requestType != RequestType.TYPE_NONE)
            requestMap.put(requestType, Status.LOADING)
    }

    private fun function(requestType: RequestType, resultListener: ResultListener): (Throwable) -> Unit {
        return {
            //log { "onError ${it.message}" }
            requestMap.replace(requestType, Status.ERROR)
            resultListener.onRequestError(requestType, it.message)
        }
    }

    private inline fun <T> ResultListener.function(requestType: RequestType, crossinline success: (T) -> Unit): (T) -> Unit {
        return {
            val status = if (it is List<*> && it.isEmpty()) {
                Status.EMPTY
            } else {
                Status.SUCCESS
            }
            requestMap.replace(requestType, status)
            onRequestSuccess(it)
            success(it)
        }
    }

    fun hasActiveRequest(): Boolean = requestMap.isNotEmpty()

    fun getRequestStatus(requestType: RequestType) = requestMap.getOrElse(requestType, { Status.IDLE })

    fun clear() {
        disposable.clear()
    }
}