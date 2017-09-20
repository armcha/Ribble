package com.luseen.ribble.presentation.adapter

import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.utils.glide.load
import kotlinx.android.synthetic.main.liked_shot_item.view.*

/**
 * Created by Chatikyan on 15.08.2017.
 */
class UserLikesRecyclerAdapter constructor(likeList: List<Like>,
                                           private val shotClickListener: ShotClickListener)
    : AbstractAdapter<Like>(likeList, R.layout.liked_shot_item) {

    override fun onItemClick(itemView: View, position: Int) {
        val shot = itemList[position].shot
        shot?.let {
            shotClickListener.onShotClicked(shot)
        }
    }

    override fun View.bind(item: Like) {
        val shot = item.shot
        shotTitle.text = shot?.title
        shotAuthor.text = shot?.user?.name
        likeCount.text = shot?.likesCount.toString()
        shotImage.load(shot?.image?.normal)
    }
}