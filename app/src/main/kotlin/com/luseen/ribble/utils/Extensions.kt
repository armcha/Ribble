package com.luseen.ribble.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v4.content.ContextCompat
import com.luseen.logger.Logger

/**
 * Created by Chatikyan on 01.08.2017.
 */


inline fun log(message: () -> Any?) {
    Logger.log(message())
}

fun log(message: Any?) {
    Logger.log(message)
}

infix fun Context.takeColor(colorId: Int) = ContextCompat.getColor(this, colorId)

fun UI(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        action()
    }
}
