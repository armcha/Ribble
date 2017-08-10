package com.luseen.ribble.presentation.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.luseen.ribble.presentation.model.Shot
import kotlinx.android.synthetic.main.shot_item.view.*

/**
 * Created by Chatikyan on 04.08.2017.
 */
class ShotRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(shot: Shot) {
        itemView.shotTitle.text = shot.title
    }
}