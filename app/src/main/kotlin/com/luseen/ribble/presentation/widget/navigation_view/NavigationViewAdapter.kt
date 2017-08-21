package com.luseen.ribble.presentation.widget.navigation_view

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.luseen.ribble.R
import com.luseen.ribble.presentation.adapter.AbstractAdapter
import com.luseen.ribble.utils.inflate
import com.luseen.ribble.utils.tint
import kotlinx.android.synthetic.main.navigation_view_item.view.*

/**
 * Created by Chatikyan on 20.08.2017.
 */
class NavigationViewAdapter constructor(private val itemList: MutableList<NavigationItem>)
    : AbstractAdapter<NavigationViewAdapter.NavigationViewHolder, NavigationItem>(itemList) {

    var itemClickListener: ItemClickListener? = null

    override fun createViewHolder(parent: ViewGroup): NavigationViewHolder {
        val view = parent inflate R.layout.navigation_view_item
        val holder = NavigationViewHolder(view)
        val itemView = holder.itemView

        itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                val position = holder.adapterPosition
                val item = itemList[position]
                itemClickListener?.onNavigationItemClick(item, position)
            }
        }
        return holder
    }

    override fun onBind(holder: NavigationViewHolder, item: NavigationItem) {
        holder.bind(item)
    }

    class NavigationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(navigationItem: NavigationItem) {
            itemView.itemText.text = navigationItem.name
            itemView.itemIcon.setImageResource(navigationItem.icon)
            itemView.itemIcon.tint(R.color.colorPrimary)
            if (navigationItem.isSelected)
                itemView.setBackgroundColor(Color.LTGRAY)
            else
                itemView.setBackgroundColor(Color.WHITE)
        }
    }
}