package com.luseen.ribble.utils.extensions

import com.luseen.logger.Logger

/**
 * Created by Chatikyan on 14.09.2017.
 */

inline fun log(message: () -> Any?) {
    Logger.log(message())
}

fun Any.makeLog() {
    Logger.log("${this.javaClass.simpleName} $this")
}

fun log(vararg message: () -> Any?) {
    message.forEach {
        Logger.log(it())
    }
}

fun log(message: Any?) {
    Logger.log(message)
}