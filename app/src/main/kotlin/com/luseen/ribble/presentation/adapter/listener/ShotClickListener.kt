package com.luseen.ribble.presentation.adapter.listener

import android.view.View
import com.luseen.ribble.domain.entity.Shot

/**
 * Created by Chatikyan on 05.08.2017.
 */
interface ShotClickListener {

    fun onShotClicked(card: View, shot: Shot)
}