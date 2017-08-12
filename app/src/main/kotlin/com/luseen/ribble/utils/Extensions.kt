package com.luseen.ribble.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.support.v4.content.ContextCompat
import android.widget.Toast
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

fun UIThread(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        action()
    }
}

inline fun Activity.start(clazz: () -> Class<*>) {
    this.startActivity(Intent(this, clazz()))
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

//fun <F, S, R> withH(first: F, second: S, block: () -> R): R {
//    return with(first) {
//        with(second) {
//            block()
//        }
//    }
//}
