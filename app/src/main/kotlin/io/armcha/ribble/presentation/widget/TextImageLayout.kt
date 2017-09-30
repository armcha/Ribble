package io.armcha.ribble.presentation.widget

import android.content.Context
import android.graphics.Color
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import io.armcha.ribble.presentation.utils.extensions.takeColor

/**
 * Created by Chatikyan on 29.08.2017.
 */
class TextImageLayout : LinearLayout {

    private val circleImage: CircleLinedImageView
    private val textView: TextView

    var layoutText: Int? = 0
        set(value) {
            textView.text = value.toString()
        }
    var imageResId = 0
        set(value) {
            circleImage.setImageResource(value)

        }
    var imageTint: Int = 0
        set(value) {
            val wrap = DrawableCompat.wrap(circleImage.drawable)
            DrawableCompat.setTint(wrap, context.takeColor(value))
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        circleImage = CircleLinedImageView(context)
        textView = TextView(context)
        addView(circleImage)
        addView(textView)
        with(textView) {
            textSize = 15F
            setTextColor(Color.GRAY)
            text = layoutText.toString()
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }
}