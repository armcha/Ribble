package com.luseen.ribble.di.component

import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.di.module.ApiModule
import com.luseen.ribble.di.module.ApplicationModule
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

    fun plus(activityModule: ActivityModule): ActivityComponent
}