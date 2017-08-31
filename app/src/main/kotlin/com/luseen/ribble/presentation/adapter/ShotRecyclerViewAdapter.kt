package com.luseen.ribble.presentation.adapter

import android.view.View
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
        return ShotViewHolder(shotView)
    }

    override fun onItemClick(itemView: View, position: Int) {
        shotClickListener.onShotClicked(itemList[position])
    }

    override fun onBind(holder: ShotViewHolder, item: Shot) {
        holder.bind(item)
    }

    override fun onViewRecycled(itemView: View) {
        itemView.image.clear()
    }
}