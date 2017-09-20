package com.luseen.ribble.presentation.adapter

import android.view.View

/**
 * Created by Chatikyan on 20.09.2017.
 */
class RibbleAdapter<ITEM>(items: List<ITEM>,
                          layoutResId: Int,
                          private val bindHolder: View.(ITEM) -> Unit) : AbstractAdapter<ITEM>(items, layoutResId) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(itemList[position])
    }
}