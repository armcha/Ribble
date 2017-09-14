package com.luseen.ribble.presentation.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
import com.luseen.ribble.utils.AnimationUtils
import com.luseen.ribble.utils.hide
import com.luseen.ribble.utils.show


/**
 * Created by Chatikyan on 04.09.2017.
 */
class CircleProgressView : View, Animatable {

    private val MAX_VALUE = 360F
    private val END_VALUE = 280F
    private val START_VALUE = 15F

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
            paint.color = field
            invalidate()
        }
    var backgroundCircleColor = Color.parseColor("#80ffffff")
        set(value) {
            field = value
            backgroundPaint.color = field
            invalidate()
        }
    var progresTicknes = 11F
        set(value) {
            field = value
            paint.strokeWidth = field
            invalidate()
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        if (!isInEditMode)
            hide()
        paint.color = progressColor
        paint.strokeWidth = progresTicknes
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        backgroundPaint.color = backgroundCircleColor
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
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

        val halfSize = height.toFloat() / 2
        canvas.drawCircle(halfSize, halfSize, halfSize, backgroundPaint)
        canvas.drawArc(rectF, start, progress, false, paint)
    }

    private fun startProgress() {
        var started = true
        with(progressAnimator) {
            duration = 700L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE

            val diff = MAX_VALUE - END_VALUE
            var currentPoint = start - diff

            addUpdateListener {
                progress = it.animatedValue as Float
                if (Math.round(progress) >= END_VALUE || started) {
                    started = true
                    start = currentPoint - progress
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
        val to = if (isReverse) 0F else 1F

        if (!isReverse) {
            this.scale = 0F
        }
        this.animate()
                .scaleX(to)
                .scaleY(to)
                .setDuration(200)
                .withLayer()
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        endBody()
                    }
                }).start()
    }

    private var View.scale: Float
        get() = this.scaleX
        set(value) {
            this.scaleY = value
            this.scaleX = value
        }
}