package com.luseen.ribble.presentation.adapter

import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.utils.glide.clear
import com.luseen.ribble.utils.glide.load
import kotlinx.android.synthetic.main.shot_item.view.*

/**
 * Created by Chatikyan on 04.08.2017.
 */
class ShotRecyclerViewAdapter constructor(
        shotList: List<Shot>,
        private val shotClickListener: ShotClickListener)
    : AbstractAdapter<Shot>(shotList, R.layout.shot_item) {

    override fun onItemClick(itemView: View, position: Int) {
        shotClickListener.onShotClicked(itemList[position])
    }

    override fun onViewRecycled(itemView: View) {
        itemView.image.clear()
    }

    override fun View.bind(item: Shot) {
        shotTitle.text = item.title
        shotAuthor.text = item.user.name
        val shotImage = item.image
        image?.let {
            image.load(shotImage.normal)
        }
    }
}