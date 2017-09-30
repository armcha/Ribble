package io.armcha.ribble.presentation.widget.navigation_view

import io.armcha.ribble.presentation.screen.shot_detail.ShotDetailFragment
import io.armcha.ribble.presentation.utils.extensions.emptyString

/**
 * Created by Chatikyan on 21.08.2017.
 */
sealed class NavigationId(val name: String = emptyString, val fullName: String = emptyString) {

    object SHOT : NavigationId("SHOT")
    object USER_LIKES : NavigationId("USER LIKES")
    object FOLLOWING : NavigationId("FOLLOWING")
    object ABOUT : NavigationId("APP INFO")
    object LOG_OUT : NavigationId("LOG OUT")
    object SHOT_DETAIL : NavigationId("SHOT DETAIL", ShotDetailFragment::class.java.name)
}