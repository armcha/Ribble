package com.luseen.ribble.presentation.navigation

/**
 * Created by Chatikyan on 21.09.2017.
 */
@Experimental
sealed class BackStrategy {

    object KEEP : BackStrategy()
    object DESTROY : BackStrategy()
}