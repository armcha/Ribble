package io.armcha.ribble.presentation.navigation

import io.armcha.ribble.presentation.utils.Experimental

/**
 * Created by Chatikyan on 21.09.2017.
 */
@Experimental
sealed class BackStrategy {

    object KEEP : BackStrategy()
    object DESTROY : BackStrategy()
}