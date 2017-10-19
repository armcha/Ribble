package io.armcha.ribble.presentation.widget

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Created by Chatikyan on 16.02.2017.
 */

class AnimatedImageView : AppCompatImageView, AnimatedView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setAnimatedImage(newImage: Int, startDelay: Long = 0L) {
        changeImage(newImage, startDelay)
    }

    private fun changeImage(newImage: Int, startDelay: Long) {
        if (tag == newImage)
            return
        animate(view = this, startDelay = startDelay) {
            setImageResource(newImage)
            tag = newImage
        }
    }
}