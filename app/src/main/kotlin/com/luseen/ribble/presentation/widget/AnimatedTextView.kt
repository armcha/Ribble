package com.luseen.ribble.presentation.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.luseen.ribble.utils.AnimationUtils


class AnimatedTextView : AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setAnimatedText(text: CharSequence) {
        changeText(text, 0)
    }

    fun setAnimatedText(text: CharSequence, startDelay: Long) {
        changeText(text, startDelay)
    }

    private fun changeText(newText: CharSequence, startDelay: Long) {
        val fastOutSlowInterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR
        if (animation != null) {
            animation.cancel()
        }
        animate()
                .alpha(0f)
                .scaleX(0.8f)
                .setDuration(200)
                .setStartDelay(startDelay)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        text = newText
                        scaleX = 0.8f
                        animate()
                                .scaleX(1f)
                                .alpha(1f)
                                .setListener(null)
                                .setStartDelay(0)
                                .setDuration(200)
                                .setInterpolator(fastOutSlowInterpolator)
                                .start()
                    }
                })
                .start()
    }

}
