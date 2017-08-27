package com.luseen.ribble.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.luseen.ribble.R
import com.luseen.ribble.utils.toPx

/**
 * Created by Chatikyan on 27.08.2017.
 */
class CircleLinedImageView(context: Context) : AppCompatImageView(context) {

    private val LINE_WIDTH = 3F

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var padding = 17

    constructor(context: Context, attributeSet: AttributeSet) : this(context)

    init {
        paint.color = Color.LTGRAY
        paint.strokeWidth = LINE_WIDTH
        paint.style = Paint.Style.STROKE
        setImageResource(R.drawable.ic_menu_gallery) //TODO remove
        padding = padding.toPx(context)
        setPadding(padding, padding, padding, padding)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val actualWidth = (width - LINE_WIDTH) / 2F
        canvas.drawCircle(width / 2F, width / 2F, actualWidth, paint)
    }
}