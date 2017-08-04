package com.luseen.ribble

import android.app.Application
import com.luseen.logger.LogType
import com.luseen.logger.Logger
import com.luseen.ribble.di.component.ApplicationComponent
import com.luseen.ribble.di.component.DaggerApplicationComponent
import com.luseen.ribble.di.module.ApiModule
import com.luseen.ribble.di.module.ApplicationModule


/**
 * Created by Chatikyan on 29.07.2017.
 */
class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    companion object {
        @JvmStatic
        var instance = App()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initApplicationComponent()
        initLogger()
    }

    private fun initLogger() {
        Logger.Builder()
                .isLoggable(BuildConfig.DEBUG)
                .logType(LogType.ERROR)
                .tag("Ribble")
                .build()
    }

    private fun initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule())
                .build()

        applicationComponent.inject(this)
    }
}