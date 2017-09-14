package com.luseen.ribble.presentation.widget.navigation_view

import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailFragment
import com.luseen.ribble.utils.emptyString

/**
 * Created by Chatikyan on 21.08.2017.
 */
sealed class NavigationId(val name: String = emptyString(), val fullName: String = emptyString()) {

    object SHOT : NavigationId("SHOT")
    object USER_LIKES : NavigationId("USER LIKES")
    object FOLLOWING : NavigationId("FOLLOWING")
    object ABOUT : NavigationId("ABOUT")
    object LOG_OUT : NavigationId("LOG OUT")
    object SHOT_DETAIL : NavigationId("SHOT DETAIL", ShotDetailFragment::class.java.name)
}