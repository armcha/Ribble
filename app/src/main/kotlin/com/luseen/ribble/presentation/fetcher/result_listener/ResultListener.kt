package com.luseen.ribble.presentation.base_mvp.result_listener

/**
 * Created by Chatikyan on 04.08.2017.
 */
interface ResultListener {

    fun onRequestStart()

    fun <T> onRequestSuccess(data: T)

    fun onRequestError(errorMessage: String?)
}