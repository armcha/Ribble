package io.armcha.ribble.presentation.widget

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * Created by Chatikyan on 16.02.2017.
 */

class AnimatedTextView (context: Context, attrs: AttributeSet? = null)
    : AppCompatTextView(context, attrs), AnimatedView {

    fun setAnimatedText(text: CharSequence, startDelay: Long = 0L) {
        changeText(text, startDelay)
    }

    private fun changeText(newText: CharSequence, startDelay: Long) {
        if (text == newText)
            return
        animate(view = this, startDelay = startDelay) {
            text = newText
        }
    }
}
