package com.luseen.ribble.data.pref

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
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

    private val sharedPreferences by lazy {
        app.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        sharedPreferences.edit()
    }

    infix fun saveUserLoggedIn(isLogged: Boolean) {
        editor.putBoolean(USER_LOGGED_IN, isLogged).apply()
    }

    fun isUserLoggedIn(): Boolean = sharedPreferences.getBoolean(USER_LOGGED_IN, false)
}