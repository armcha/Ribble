package com.luseen.ribble.utils.extensions

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Chatikyan on 14.09.2017.
 */

fun TextView.leftIcon(drawableId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, drawableId), null, null, null)
}

@SuppressLint("NewApi")
fun TextView.iconTint(colorId: Int) {
    LorAbove {
        compoundDrawableTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colorId))
    }
}

var View.scale: Float
    get() = this.scaleX
    set(value) {
        this.scaleY = value
        this.scaleX = value
    }

fun View.addTopMargin(marginInDp: Int) {
    (this.layoutParams as ViewGroup.MarginLayoutParams).topMargin = marginInDp.toPx(this.context)
}

fun View.addBottomMargin(marginInDp: Int) {
    (this.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = marginInDp.toPx(this.context)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.onClick(function: () -> Unit) {
    this.setOnClickListener {
        function()
    }
}

infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(this.context).inflate(layoutResId, this, false)

fun ImageView.tint(colorId: Int) {
    this.setColorFilter(this.context.takeColor(colorId), PorterDuff.Mode.SRC_IN)
}

operator fun ViewGroup.get(index: Int): View = getChildAt(index)