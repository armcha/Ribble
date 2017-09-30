package io.armcha.ribble.presentation.widget.navigation_view

import io.armcha.ribble.R

/**
 * Created by Chatikyan on 20.08.2017.
 */
data class NavigationItem(val item: NavigationId,
                          val icon: Int,
                          var isSelected: Boolean = false,
                          val itemIconColor: Int = R.color.navigation_item_color) {

    val name: String
        get() = item.name

    val id: NavigationId
        get() = item
}