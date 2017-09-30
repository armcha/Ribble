package io.armcha.ribble.presentation.widget.navigation_view

import android.view.View
import io.armcha.ribble.presentation.adapter.AbstractAdapter
import io.armcha.ribble.presentation.utils.C
import io.armcha.ribble.presentation.utils.L
import io.armcha.ribble.presentation.utils.extensions.takeColor
import io.armcha.ribble.presentation.utils.extensions.tint
import kotlinx.android.synthetic.main.navigation_view_item.view.*

/**
 * Created by Chatikyan on 20.08.2017.
 */
class NavigationViewAdapter constructor(navigationItemList: MutableList<NavigationItem>,
                                        private var itemClickListener: ItemClickListener?)
    : AbstractAdapter<NavigationItem>(navigationItemList, L.navigation_view_item) {

    override fun onItemClick(itemView: View, position: Int) {
        itemClickListener?.let {
            it(itemList[position], position)
        }
    }

    override fun View.bind(item: NavigationItem) {
        itemText.text = item.name
        itemIcon.setImageResource(item.icon)
        itemIcon.tint(item.itemIconColor)
        if (item.isSelected) {
            itemText.setTextColor(context.takeColor(C.colorAccent))
        } else {
            itemText.setTextColor(context.takeColor(C.blue_gray))
        }
    }
}