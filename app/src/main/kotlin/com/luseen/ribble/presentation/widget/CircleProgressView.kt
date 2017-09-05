package com.luseen.ribble.presentation.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Animatable
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import com.luseen.ribble.utils.AnimationUtils
import com.luseen.ribble.utils.hide
import com.luseen.ribble.utils.show


/**
 * Created by Chatikyan on 04.09.2017.
 */
class CircleProgressView : View, Animatable {

    private val MAX_VALUE = 360F
    private val END_VALUE = 280F
    private val START_VALUE = 10F

    private val progressAnimator = ValueAnimator.ofFloat(START_VALUE, END_VALUE)
    private val rotateAnimation = RotateAnimation(0f, MAX_VALUE,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val animationSet = AnimationSet(false)
    private val rectF = RectF()
    private var progress = START_VALUE
    private var start = 0F
    private val wayFromCircle = 3F

    var progressColor = Color.WHITE
        set(value) {
            field = value
            invalidate()
        }
    var backgroundCircleColor = Color.parseColor("#80ffffff")
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        hide()
        paint.color = progressColor
        paint.strokeWidth = 13F
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND

        backgroundPaint.color = backgroundCircleColor
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        start()
    }

    override fun isRunning(): Boolean {
        return progressAnimator.isRunning && !rotateAnimation.hasEnded()
    }

    override fun stop() {
        startScale(isReverse = true) {
            hide()
            progressAnimator.cancel()
            rotateAnimation.cancel()
        }
    }

    override fun start() {
        show()
        startScale {}
        startProgress()
        startRotation()
        animation = animationSet.apply {
            start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        val strokeWidth = paint.strokeWidth + wayFromCircle
        val actualWidth = width.toFloat() - strokeWidth
        val actualHeight = height.toFloat() - strokeWidth
        rectF.set(strokeWidth, strokeWidth, actualWidth, actualHeight)
        canvas.drawCircle(height.toFloat() / 2, height.toFloat() / 2, height.toFloat() / 2, backgroundPaint)
        canvas.drawArc(rectF, start, progress, false, paint)
    }

    private fun startProgress() {
        var started = false
        with(progressAnimator) {
            duration = 800L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE

            val diff = MAX_VALUE - END_VALUE
            var currentPoint = start - diff
            progressAnimator.addUpdateListener {
                val currentValue = it.animatedValue as Float
                if (Math.round(currentValue) < END_VALUE && !started) {
                    progress = currentValue
                } else {
                    started = true
                    progress = currentValue
                    start = currentPoint - currentValue
                    if (Math.round(progress) == START_VALUE.toInt()) {
                        started = false
                        currentPoint = start - diff
                    }
                }
                invalidate()
            }
            start()
        }
    }

    private fun startRotation() {
        with(rotateAnimation) {
            duration = 1700
            repeatCount = Animation.INFINITE
            interpolator = AnimationUtils.LINEAR_INTERPOLATOR
        }
        animationSet.addAnimation(rotateAnimation)
    }

    private inline fun startScale(isReverse: Boolean = false, crossinline endBody: () -> Unit) {
        val from = if (isReverse) 1F else 0F
        val to = if (isReverse) 0F else 1F
        val scale = ScaleAnimation(
                from, to,
                from, to,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        scale.fillAfter = true
        scale.duration = 500
        scale.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(p0: Animation?) {
                endBody()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
            }
        })
        animationSet.addAnimation(scale)
    }
}