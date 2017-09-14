package com.luseen.ribble.utils.extensions

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.luseen.ribble.utils.LorAbove

/**
 * Created by Chatikyan on 14.09.2017.
 */

fun TextView.leftIcon(colorId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, colorId), null, null, null)
}

@SuppressLint("NewApi")
fun TextView.iconTint(colorId: Int) {
    LorAbove {
        compoundDrawableTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colorId))
    }
}