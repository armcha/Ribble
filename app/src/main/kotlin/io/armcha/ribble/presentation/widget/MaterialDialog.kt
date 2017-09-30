package io.armcha.ribble.presentation.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.Button
import android.widget.TextView
import io.armcha.ribble.R
import io.armcha.ribble.presentation.utils.AnimationUtils
import io.armcha.ribble.presentation.utils.extensions.nonSafeLazy
import io.armcha.ribble.presentation.utils.extensions.onClick
import io.armcha.ribble.presentation.utils.extensions.scale
import kotlinx.android.synthetic.main.dialog_item.*

/**
 * Created by Chatikyan on 10.09.2017.
 */
class MaterialDialog(context: Context) : Dialog(context, R.style.MaterialDialogSheet) {

    private val titleText by nonSafeLazy {
        findViewById<TextView>(R.id.title)
    }
    private val messageText by nonSafeLazy {
        findViewById<TextView>(R.id.message)
    }
    private val positiveButton by nonSafeLazy {
        findViewById<Button>(R.id.positiveButton)
    }

    init {
        setContentView(R.layout.dialog_item)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        dialogContainer.onClick { hide() }
    }

    override fun onDetachedFromWindow() {
        super.dismiss()
        super.onDetachedFromWindow()
    }

    infix fun title(title: String): MaterialDialog {
        titleText.text = title
        return this
    }

    infix fun message(message: String): MaterialDialog {
        messageText.text = message
        return this
    }

    fun addPositiveButton(text: String, action: MaterialDialog.() -> Unit): MaterialDialog {
        positiveButton.text = text.toUpperCase()
        positiveButton.onClick {
            action()
        }
        return this
    }

    override fun show() {
        super.show()
        val view = findViewById<View>(io.armcha.ribble.R.id.container)
        with(view) {
            alpha = 0F
            scale = .85F
            animate()
                    .scale(1F)
                    .alpha(1F)
                    .setDuration(120)
                    .setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
                    .withLayer()
                    .start()
        }
    }

    override fun hide() {
        val view = findViewById<View>(io.armcha.ribble.R.id.container)
        with(view) {
            animate()
                    .scale(.85F)
                    .alpha(0F)
                    .setDuration(120)
                    .setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
                    .withLayer()
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            this@MaterialDialog.dismiss()
                        }
                    })
                    .start()
        }
    }

    private fun ViewPropertyAnimator.scale(scale: Float): ViewPropertyAnimator {
        scaleX(scale)
        scaleY(scale)
        return this
    }
}