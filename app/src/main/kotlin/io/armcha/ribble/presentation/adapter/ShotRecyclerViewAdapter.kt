package io.armcha.ribble.presentation.adapter

import android.view.View
import io.armcha.ribble.R
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.presentation.adapter.listener.ShotClickListener
import io.armcha.ribble.presentation.utils.glide.clear
import io.armcha.ribble.presentation.utils.glide.load
import kotlinx.android.synthetic.main.shot_item.view.*

/**
 * Created by Chatikyan on 04.08.2017.
 */
class ShotRecyclerViewAdapter constructor(
        shotList: List<Shot>,
        private val shotClickListener: ShotClickListener)
    : AbstractAdapter<Shot>(shotList, io.armcha.ribble.R.layout.shot_item) {

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