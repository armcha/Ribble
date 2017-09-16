package com.luseen.ribble.di.component

import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.screen.about.AboutFragment
import com.luseen.ribble.presentation.screen.auth.AuthActivity
import com.luseen.ribble.presentation.screen.dispatch.DispatchActivity
import com.luseen.ribble.presentation.screen.home.HomeActivity
import com.luseen.ribble.presentation.screen.shot.ShotFragment
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailFragment
import com.luseen.ribble.presentation.screen.shot_root.ShotRootFragment
import com.luseen.ribble.presentation.screen.user_following.UserFollowingFragment
import com.luseen.ribble.presentation.screen.user_likes.UserLikesFragment
import dagger.Subcomponent

/**
 * Created by Chatikyan on 31.07.2017.
 */

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(homeActivity: HomeActivity)

    fun inject(shotFragment: ShotFragment)

    fun inject(shotDetailFragment: ShotDetailFragment)

    fun inject(shotRootFragment: ShotRootFragment)

    fun inject(authActivity: AuthActivity)

    fun inject(dispatchActivity: DispatchActivity)

    fun inject(userLikesFragment: UserLikesFragment)

    fun inject(userFollowingFragment: UserFollowingFragment)

    fun inject(aboutFragment: AboutFragment)
}