package com.luseen.ribble.di.component

import com.luseen.ribble.di.module.UserModule
import com.luseen.ribble.di.scope.PerUser
import com.luseen.ribble.presentation.screen.auth.AuthActivity
import dagger.Subcomponent

/**
 * Created by Chatikyan on 10.08.2017.
 */

@PerUser
@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(authActivity: AuthActivity)
}