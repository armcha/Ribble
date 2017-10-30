package io.armcha.ribble.di.component

import io.armcha.ribble.di.module.ActivityModule
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.presentation.screen.about.AboutFragment
import io.armcha.ribble.presentation.screen.auth.AuthActivity
import io.armcha.ribble.presentation.screen.dispatch.DispatchActivity
import io.armcha.ribble.presentation.screen.home.HomeActivity
import io.armcha.ribble.presentation.screen.shot.ShotFragment
import io.armcha.ribble.presentation.screen.shot_detail.ShotDetailFragment
import io.armcha.ribble.presentation.screen.shot_root.ShotRootFragment
import io.armcha.ribble.presentation.screen.user_following.UserFollowingFragment
import io.armcha.ribble.presentation.screen.user_likes.UserLikesFragment
import dagger.Subcomponent

/**
 * Created by Chatikyan on 31.07.2017.
 */

@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
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