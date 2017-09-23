package com.luseen.ribble.presentation.fetcher.result_listener

/**
 * Created by Chatikyan on 04.08.2017.
 */
interface ResultListener {

    fun onRequestStart()

    fun onRequestStart(requestType: RequestType)

    fun <T> onRequestSuccess(data: T)

    fun onRequestError(errorMessage: String?)

    fun onRequestError(requestType: RequestType, errorMessage: String?)
}