package io.armcha.ribble.presentation.widget.navigation_view

/**
 * Created by Chatikyan on 20.08.2017.
 */
interface ItemClickListener {

    operator fun invoke(item: NavigationItem, position: Int)
}