package com.luseen.ribble.presentation.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.holder.ShotViewHolder
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.utils.glide.clear
import com.luseen.ribble.utils.inflate
import kotlinx.android.synthetic.main.shot_item.view.*

/**
 * Created by Chatikyan on 04.08.2017.
 */
class ShotRecyclerViewAdapter constructor(
        shotList: List<Shot>,
        private val shotClickListener: ShotClickListener)
    : AbstractAdapter<ShotViewHolder, Shot>(shotList) {

    override fun createViewHolder(parent: ViewGroup): ShotViewHolder {
        val shotView = parent inflate R.layout.shot_item
        val viewHolder = ShotViewHolder(shotView)
        viewHolder.itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                ViewCompat.setTransitionName(viewHolder.itemView.cardView, "cardView" + adapterPosition)
                shotClickListener.onShotClicked(viewHolder.itemView.cardView, itemList[adapterPosition])
            }
        }
        return viewHolder
    }

    override fun onBind(holder: ShotViewHolder, item: Shot) {
        holder.bind(item)
    }

    override fun onViewRecycled(holder: ShotViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.image.clear()
    }
}