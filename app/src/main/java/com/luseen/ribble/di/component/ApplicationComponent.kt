package com.luseen.ribble.di.component

import android.app.Application
import com.luseen.ribble.App
import com.luseen.ribble.data.ShotDataRepository
import com.luseen.ribble.data.network.ApiService
import com.luseen.ribble.data.pref.Preferences
import com.luseen.ribble.di.module.ApiModule
import com.luseen.ribble.di.module.ApplicationModule
import com.luseen.ribble.presentation.fetcher.Fetcher
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        ApiModule::class))
interface ApplicationComponent {

    fun inject(application: App)

    fun application(): Application

    fun apiService(): ApiService

    fun preferences(): Preferences

    fun fetcher(): Fetcher

    fun shotRepository(): ShotDataRepository
}