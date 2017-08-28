package com.luseen.ribble.utils.glide

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by Chatikyan on 28.08.2017.
 */
private val DEFAULT_DURATION_MS = 200

fun ImageView.load(url: String):Unit {
    GlideApp.with(this.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(DEFAULT_DURATION_MS))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
}

fun ImageView.clear() {
    GlideApp.with(this.context)
            .clear(this)
}