package com.luseen.ribble.presentation.widget.navigation_view

import com.luseen.ribble.utils.emptyString

/**
 * Created by Chatikyan on 21.08.2017.
 */
sealed class NavigationId(val name: String = emptyString()) {

    object SHOT : NavigationId("SHOT")
    object USER_LIKES : NavigationId("USER LIKES")
    object FOLLOWING : NavigationId("FOLLOWING")
    object TEST_2 : NavigationId("TEST_2")
    object LOG_OUT : NavigationId("LOG OUT")
    object SHOT_DETAIL : NavigationId()
}