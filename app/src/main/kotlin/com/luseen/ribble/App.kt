package com.luseen.ribble

import android.app.Application
import com.luseen.logger.LogType
import com.luseen.logger.Logger
import com.luseen.ribble.di.component.ApplicationComponent
import com.luseen.ribble.di.component.DaggerApplicationComponent
import com.luseen.ribble.di.module.ApplicationModule
import com.luseen.ribble.presentation.utils.extensions.nonSafeLazy
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Chatikyan on 29.07.2017.
 */
class App : Application() {

    val applicationComponent: ApplicationComponent by nonSafeLazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    companion object {
        var instance = App()
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        instance = this
        initLogger()
    }

    private fun initLogger() {
        Logger.Builder()
                .isLoggable(BuildConfig.DEBUG)
                .logType(LogType.ERROR)
                .tag("Ribble")
                .setIsKotlin(true)
                .build()
    }
}