package com.luseen.ribble.data.pref

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@SuppressLint("CommitPrefEdits")
@Singleton
class Preferences @Inject constructor(app: Application) {

    private val SHARED_PREF_NAME = "ribble_shared_pref"
    private val USER_LOGGED_IN = "user_logged_in"

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        app.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    infix fun saveUserLoggedIn(isLogged: Boolean) {
        sharedPreferences.put {
            putBoolean(USER_LOGGED_IN, isLogged)
        }
    }

    fun isUserLoggedIn(): Boolean = sharedPreferences.getBoolean(USER_LOGGED_IN, false)
}

private inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
    val editor = this.edit()
    editor.body()
    editor.apply()
}