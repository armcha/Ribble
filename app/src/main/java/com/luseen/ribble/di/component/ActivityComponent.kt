package com.luseen.ribble.di.component

import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.di.module.UserModule
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.screen.home.HomeActivity
import com.luseen.ribble.presentation.screen.shot.PapularShotFragment
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailActivity
import com.luseen.ribble.presentation.screen.shot_root.ShotRootFragment
import dagger.Subcomponent

/**
 * Created by Chatikyan on 31.07.2017.
 */

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun plus(userModule: UserModule): UserComponent

    fun inject(homeActivity: HomeActivity)

    fun inject(popularShotFragment: PapularShotFragment)

    fun inject(shotDetailActivity: ShotDetailActivity)

    fun inject(shotRootFragment: ShotRootFragment)
}