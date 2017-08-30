package com.luseen.ribble.presentation.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.utils.glide.load
import kotlinx.android.synthetic.main.shot_item.view.*

/**
 * Created by Chatikyan on 04.08.2017.
 */
class ShotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(shot: Shot) {
        with(itemView){
            shotTitle.text = shot.title
            shotAuthor.text = shot.user.name
            val shotImage = shot.image
            image?.let {
                image.load(shotImage.normal)
            }
        }

    }
}