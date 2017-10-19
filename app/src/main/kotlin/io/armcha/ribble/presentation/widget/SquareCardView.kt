package io.armcha.ribble.presentation.widget

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet

/**
 * Created by Chatikyan on 08.08.2017.
 */

class SquareCardView constructor(context: Context, attrs: AttributeSet) : CardView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}