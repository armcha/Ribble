package com.luseen.ribble.presentation.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.luseen.ribble.R
import com.luseen.ribble.utils.extensions.nonSafeLazy
import com.luseen.ribble.utils.extensions.onClick

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
        val view = findViewById<View>(R.id.container)
        with(view) {
            alpha = 0F
            scaleY = 0.80F
            scaleX = 0.80F
            animate()
                    .scaleY(1F)
                    .scaleX(1F)
                    .alpha(1F)
                    .setDuration(130)
                    .withLayer()
                    .start()
        }
    }

    override fun hide() {
        val view = findViewById<View>(R.id.container)
        with(view) {
            animate()
                    .scaleY(0.88F)
                    .scaleX(0.88F)
                    .alpha(0F)
                    .setDuration(150)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            this@MaterialDialog.dismiss()
                        }
                    })
                    .start()
        }
    }
}