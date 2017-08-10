package com.luseen.ribble.data.pref

import android.app.Application
import com.luseen.logger.Logger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Singleton
class Preferences @Inject constructor(app: Application) {

    fun getSomeBool():Boolean = true //TODO fix

    init {
        Logger.log(app)
    }
}