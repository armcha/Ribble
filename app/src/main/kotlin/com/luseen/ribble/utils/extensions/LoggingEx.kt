package com.luseen.ribble.utils.extensions

import com.luseen.logger.Logger

/**
 * Created by Chatikyan on 14.09.2017.
 */

inline fun log(message: () -> Any?) {
    Logger.log(message())
}

inline fun <reified T> T.withLog(): T {
    Logger.log("${T::class.java.simpleName} $this")
    return this
}

fun log(vararg message: () -> Any?) {
    message.forEach {
        Logger.log(it())
    }
}

fun log(message: Any?) {
    Logger.log(message)
}