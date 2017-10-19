package io.armcha.ribble.presentation.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Animatable
import android.support.annotation.DimenRes
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import io.armcha.ribble.R
import io.armcha.ribble.presentation.utils.AnimationUtils
import io.armcha.ribble.presentation.utils.extensions.hide
import io.armcha.ribble.presentation.utils.extensions.show

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
    private val rectF = RectF()
    private var progress = START_VALUE
    private var start = 0F
    private val wayFromCircle = 3F

    var progressColor = Color.WHITE
        set(value) {
            field = value
            paint.color = field
        }
    var backgroundCircleColor = Color.parseColor("#80ffffff")
        set(value) {
            field = value
            backgroundPaint.color = field
        }
    var progresTicknes: Int = R.dimen.progress_tickness
        set(@DimenRes value) {
            field = value
            paint.strokeWidth = resources.getDimension(field)
        }
        get() = resources.getDimension(field).toInt()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        if (!isInEditMode)
            hide()
        with(paint) {
            color = progressColor
            strokeWidth = progresTicknes.toFloat()
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        backgroundPaint.color = backgroundCircleColor
    }

    override fun onDetachedFromWindow() {
        dismiss()
        super.onDetachedFromWindow()
    }

    override fun isRunning() = progressAnimator.isRunning && !rotateAnimation.hasEnded()

    override fun stop() {
        startAlpha(isReverse = true) {
            dismiss()
        }
    }

    override fun start() {
        show()
        startAlpha {}
        startProgress()
        startRotation()
    }

    fun dismiss() {
        progressAnimator.cancel()
        rotateAnimation.cancel()
        clearAnimation()
        hide()
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
            duration = 650L
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
            duration = 1100
            repeatCount = Animation.INFINITE
            interpolator = AnimationUtils.LINEAR_INTERPOLATOR
        }
        animation = rotateAnimation
    }

    private inline fun startAlpha(isReverse: Boolean = false, crossinline endBody: () -> Unit) {
        val to = if (isReverse) 0F else 1F

        if (!isReverse) {
            this.alpha = 0F
        }
        this.animate()
                .alpha(to)
                .setDuration(160)
                .withLayer()
                .withEndAction { endBody() }
                .start()
    }
}