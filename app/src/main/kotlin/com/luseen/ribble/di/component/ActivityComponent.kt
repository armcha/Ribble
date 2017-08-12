package com.luseen.ribble.di.component

import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.screen.auth.AuthActivity
import com.luseen.ribble.presentation.screen.dispatch.DispatchActivity
import com.luseen.ribble.presentation.screen.home.HomeActivity
import com.luseen.ribble.presentation.screen.popular_shot.PapularShotFragment
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailActivity
import com.luseen.ribble.presentation.screen.shot_root.ShotRootFragment
import dagger.Subcomponent

/**
 * Created by Chatikyan on 31.07.2017.
 */

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(homeActivity: HomeActivity)

    fun inject(popularShotFragment: PapularShotFragment)

    fun inject(shotDetailActivity: ShotDetailActivity)

    fun inject(shotRootFragment: ShotRootFragment)

    fun inject(authActivity: AuthActivity)

    fun inject(dispatchActivity: DispatchActivity)
}