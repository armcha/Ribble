package com.luseen.ribble.presentation.utils.extensions

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
    MorAbove {
        compoundDrawableTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colorId))
    }
}

var View.scale: Float
    get() = scaleX
    set(value) {
        scaleY = value
        scaleX = value
    }

fun View.addTopMargin(marginInDp: Int) {
    (layoutParams as ViewGroup.MarginLayoutParams).topMargin = marginInDp.toPx(this.context)
}

fun View.addBottomMargin(marginInDp: Int) {
    (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = marginInDp.toPx(this.context)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.onClick(function: () -> Unit) {
    setOnClickListener {
        function()
    }
}

infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)

fun ImageView.tint(colorId: Int) {
    setColorFilter(context.takeColor(colorId), PorterDuff.Mode.SRC_IN)
}

operator fun ViewGroup.get(index: Int): View = getChildAt(index)