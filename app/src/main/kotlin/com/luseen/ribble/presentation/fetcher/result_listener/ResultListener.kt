package com.luseen.ribble.presentation.base_mvp.result_listener

/**
 * Created by Chatikyan on 04.08.2017.
 */
interface ResultListener<T> {

    fun onRequestStart()

    fun onRequestSuccess(data: T)

    fun onRequestError(errorMessage: String?)
}