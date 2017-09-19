package com.luseen.ribble.presentation.widget.navigation_view

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.luseen.ribble.R
import com.luseen.ribble.presentation.adapter.AbstractAdapter
import com.luseen.ribble.utils.extensions.inflate
import com.luseen.ribble.utils.extensions.tint
import kotlinx.android.synthetic.main.navigation_view_item.view.*

/**
 * Created by Chatikyan on 20.08.2017.
 */
class NavigationViewAdapter constructor(navigationItemList: MutableList<NavigationItem>)
    : AbstractAdapter<NavigationViewAdapter.NavigationViewHolder, NavigationItem>(navigationItemList) {

    var itemClickListener: ItemClickListener? = null

    override fun createViewHolder(parent: ViewGroup): NavigationViewHolder {
        val view = parent inflate R.layout.navigation_view_item
        return NavigationViewHolder(view)
    }

    override fun onItemClick(itemView: View, position: Int) {
        itemClickListener?.onNavigationItemClick(itemList[position], position)
    }

    override fun onBind(holder: NavigationViewHolder, item: NavigationItem) {
        holder.bind(item)
    }

    class NavigationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(navigationItem: NavigationItem) {
            with(itemView) {
                itemText.text = navigationItem.name
                itemIcon.setImageResource(navigationItem.icon)
                itemIcon.tint(navigationItem.itemIconColor)
                if (navigationItem.isSelected)
                    setBackgroundColor(Color.LTGRAY)
                else
                    setBackgroundColor(Color.WHITE)
            }
        }
    }
}