package com.luseen.ribble.presentation.base_mvp.result_listener

/**
 * Created by Chatikyan on 04.08.2017.
 */
interface ResultListener {

    fun onStart()

    fun <T> onSuccess(data: T)

    fun onError(errorMessage: String?)
}