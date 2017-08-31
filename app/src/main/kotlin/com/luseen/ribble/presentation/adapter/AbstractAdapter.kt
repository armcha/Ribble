package com.luseen.ribble.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Chatikyan on 14.08.2017.
 */
abstract class AbstractAdapter<HOLDER : RecyclerView.ViewHolder, ITEM>
constructor(protected var itemList: List<ITEM>) : RecyclerView.Adapter<HOLDER>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HOLDER {
        val viewHolder = createViewHolder(parent)
        val itemView = viewHolder.itemView
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: HOLDER, position: Int) {
        val item = itemList[position]
        onBind(holder, item)
    }

    fun update(itemList: List<ITEM>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    final override fun onViewRecycled(holder: HOLDER) {
        super.onViewRecycled(holder)
        onViewRecycled(holder.itemView)
    }

    protected abstract fun onBind(holder: HOLDER, item: ITEM)

    protected abstract fun createViewHolder(parent: ViewGroup): HOLDER

    protected open fun onViewRecycled(itemView: View) {
    }

    protected open fun onItemClick(itemView: View, position: Int) {
    }
}