package com.luseen.ribble.presentation.widget.navigation_view

import android.graphics.Color
import android.view.View
import com.luseen.ribble.presentation.adapter.AbstractAdapter
import com.luseen.ribble.utils.L
import com.luseen.ribble.utils.extensions.tint
import kotlinx.android.synthetic.main.navigation_view_item.view.*

/**
 * Created by Chatikyan on 20.08.2017.
 */
class NavigationViewAdapter constructor(navigationItemList: MutableList<NavigationItem>,
                                        private var itemClickListener: ItemClickListener?)
    : AbstractAdapter<NavigationItem>(navigationItemList, L.navigation_view_item) {

    override fun onItemClick(itemView: View, position: Int) {
        itemClickListener?.onNavigationItemClick(itemList[position], position)
    }

    override fun View.bind(item: NavigationItem) {
        itemText.text = item.name
        itemIcon.setImageResource(item.icon)
        itemIcon.tint(item.itemIconColor)
        if (item.isSelected)
            setBackgroundColor(Color.LTGRAY)
        else
            setBackgroundColor(Color.WHITE)
    }
}