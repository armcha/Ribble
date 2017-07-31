package com.luseen.ribble

import android.app.Application
import com.luseen.logger.LogType
import com.luseen.logger.Logger
import com.luseen.ribble.di.component.AppComponent
import com.luseen.ribble.di.component.DaggerAppComponent
import com.luseen.ribble.di.module.AppModule
import com.luseen.ribble.di.module.HostModule
import com.luseen.ribble.di.module.OkHttpModule
import com.luseen.ribble.di.module.RetroFitModule


/**
 * Created by Chatikyan on 29.07.2017.
 */
class App : Application() {

    lateinit var appComponent: AppComponent

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
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .hostModule(HostModule())
                .okHttpModule(OkHttpModule())
                .retroFitModule(RetroFitModule())
                .build()
        appComponent.inject(this)
    }
}