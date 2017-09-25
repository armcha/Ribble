package com.luseen.ribble.domain.interactor

import com.luseen.ribble.domain.fetcher.result_listener.RequestType

/**
 * Created by Chatikyan on 25.09.2017.
 */
abstract class Interactor {

    val cache = hashMapOf<RequestType, Any>()

    fun <T : Any> put(key: RequestType, value: T) {
        cache.put(key, value)
    }

    inline fun <reified T> get(key: RequestType): T {
        return cache[key] as T
    }
}