package com.luseen.ribble.di.component

import android.app.Application
import com.luseen.ribble.App
import com.luseen.ribble.data.network.ApiService
import com.luseen.ribble.data.pref.Preferences
import com.luseen.ribble.di.module.AppModule
import com.luseen.ribble.di.module.HostModule
import com.luseen.ribble.di.module.OkHttpModule
import com.luseen.ribble.di.module.RetroFitModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        HostModule::class,
        OkHttpModule::class,
        RetroFitModule::class))
interface AppComponent {

    fun inject(application: App)

    fun application(): Application

    fun apiService(): ApiService

    fun preferences(): Preferences
}