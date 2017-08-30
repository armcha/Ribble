package com.luseen.ribble.presentation.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.utils.glide.load
import kotlinx.android.synthetic.main.liked_shot_item.view.*

/**
 * Created by Chatikyan on 15.08.2017.
 */
class UserLikesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(like: Like) {
        with(itemView) {
            val shot = like.shot
            shotTitle.text = shot?.title
            shotAuthor.text = shot?.user?.name
            likeCount.text = shot?.likesCount.toString()
            shotImage.load(shot?.image?.normal)
        }
    }
}