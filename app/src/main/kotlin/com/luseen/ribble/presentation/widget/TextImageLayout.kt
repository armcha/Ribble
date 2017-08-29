package com.luseen.ribble.presentation.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.luseen.ribble.utils.emptyString


/**
 * Created by Chatikyan on 29.08.2017.
 */
class TextImageLayout : LinearLayout {

    var layoutText = emptyString()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        val circleImage = CircleLinedImageView(context)
        val textView = TextView(context)
        addView(circleImage)
        addView(textView)
        with(textView) {
            textSize = 15F
            setTextColor(Color.GRAY)
            text = "245"
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }
}