package com.luseen.ribble.presentation.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.luseen.ribble.domain.entity.Like
import kotlinx.android.synthetic.main.liked_shot_item.view.*

/**
 * Created by Chatikyan on 15.08.2017.
 */
class UserLikesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(like: Like) {
        itemView.shotTitle.text = like.shot?.title
    }
}