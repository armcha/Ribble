package com.luseen.ribble.presentation.widget.navigation_view

/**
 * Created by Chatikyan on 20.08.2017.
 */
data class NavigationItem(val item: NavigationId, val icon: Int, var isSelected: Boolean = false) {

    val name: String
        get() {
            return item.name
        }

    val id: NavigationId
        get() {
            return item
        }
}