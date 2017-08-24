package com.luseen.ribble.presentation.adapter

import android.support.v7.widget.RecyclerView
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
        return createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HOLDER, position: Int) {
        val item = itemList[position]
        onBind(holder, item)
    }

    protected abstract fun onBind(holder: HOLDER, item: ITEM)

    protected abstract fun createViewHolder(parent: ViewGroup): HOLDER

    fun update(itemList: List<ITEM>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: HOLDER) {
        super.onViewRecycled(holder)
        //TODO clear glide
    }
}