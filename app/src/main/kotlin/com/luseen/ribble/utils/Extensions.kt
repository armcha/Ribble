package com.luseen.ribble.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.luseen.logger.Logger

/**
 * Created by Chatikyan on 01.08.2017.
 */


inline fun log(message: () -> Any?) {
    Logger.log(message())
}

fun log(vararg message: () -> Any?) {
    message.forEach {
        Logger.log(it())
    }
}

fun log(message: Any?) {
    Logger.log(message)
}

infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(this.context).inflate(layoutResId, this, false)

infix fun Context.takeColor(colorId: Int) = ContextCompat.getColor(this, colorId)

fun ImageView.tint(colorId: Int) {
    this.setColorFilter(this.context.takeColor(colorId), PorterDuff.Mode.SRC_IN)
}

inline fun UIThread(crossinline action: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        action()
    }
}

inline fun delay(milliseconds: Long, crossinline action: () -> Unit) {
    Handler().postDelayed({
        action()
    }, milliseconds)
}

inline fun Activity.start(clazz: () -> Class<*>) {
    this.startActivity(Intent(this, clazz()))
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <K, V> MutableMap<K, V>.replaceValue(key: K, value: V) {
    this.remove(key)
    this.put(key, value)
}

fun emptyString() = ""

fun DrawerLayout.lock() {
    this.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
}

fun DrawerLayout.unlock() {
    this.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
}

fun Fragment.whitArgument(key: String, value: Any) {
    val args = Bundle()
    if (value is Parcelable) {
        args.putParcelable(key, value)
    } else {
        throw UnsupportedOperationException("Only parcelable supported")
    }
    arguments = args
}

infix fun <T> Fragment.getExtra(key: String): T {
    val value: Any = arguments[key]
    return value as T
}

inline fun FragmentManager.inTransaction(transaction: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = this.beginTransaction()
    fragmentTransaction.transaction()
    fragmentTransaction.commit()
}
