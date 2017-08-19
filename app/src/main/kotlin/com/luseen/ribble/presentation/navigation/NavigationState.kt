package com.luseen.ribble.presentation.navigation

/**
 * Created by Chatikyan on 17.08.2017.
 */
data class NavigationState constructor(
        var activeTag: String? = null,
        var firstTag: String? = null
) {
    fun clear() {
        activeTag = null
        firstTag = null
    }
}