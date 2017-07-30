package com.luseen.ribble.di.module

import android.app.Application
import android.preference.Preference
import com.luseen.ribble.data.pref.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun providePreferences(application: Application):Preferences{
        return Preferences(application)
    }
}