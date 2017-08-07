package com.luseen.ribble.presentation.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import com.luseen.ribble.utils.log

class ArcView constructor(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    val path = Path()
    val rect = RectF()
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setBackgroundColor(Color.TRANSPARENT)
        with(paint) {
            color = Color.RED //TODO change
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        log("onDraw")
        val arcStartPoint = 2.5F
        rect.set(width.toFloat() / arcStartPoint, 0F, width.toFloat(), height.toFloat())
        with(path) {
            val halfWidth = width.toFloat() / 2
            lineTo(halfWidth + halfWidth / arcStartPoint, 0F)
            addArc(rect, 270F, 180F)
            lineTo(halfWidth + halfWidth / arcStartPoint, height.toFloat())
            lineTo(0F, height.toFloat())
            lineTo(0F, 0F)
        }
        canvas.drawPath(path, paint)
    }

}
