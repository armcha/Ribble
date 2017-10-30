package io.armcha.ribble.di.component

import io.armcha.ribble.di.module.ActivityModule
import io.armcha.ribble.di.module.ApiModule
import io.armcha.ribble.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (ApiModule::class)])
interface ApplicationComponent {

    operator fun plus(activityModule: ActivityModule): ActivityComponent
}