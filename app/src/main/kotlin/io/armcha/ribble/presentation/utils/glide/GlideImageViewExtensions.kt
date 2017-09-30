package io.armcha.ribble.presentation.utils.glide

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by Chatikyan on 28.08.2017.
 */
private val DEFAULT_DURATION_MS = 200

fun ImageView.load(url: String?) {
    load(this, url)
}

fun ImageView.load(url: String?, transformationType: TransformationType) {
    load(this, url, transformationType)
}

@JvmName("privateLoad")
private fun load(view: ImageView,
                 url: String?,
                 transformationType: TransformationType = TransformationType.NOTHING) {
    val glideRequest: GlideRequest<Drawable> = GlideApp.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(DEFAULT_DURATION_MS))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    if (transformationType != TransformationType.NOTHING) {
        glideRequest.transform(transformationType.getTransformation())
    }
    glideRequest.into(view)
}

fun ImageView.clear() {
    GlideApp.with(context)
            .clear(this)
}

enum class TransformationType {
    CIRCLE,
    ROUND,
    NOTHING;

    fun getTransformation(): Transformation<Bitmap> = when (this) {
        CIRCLE -> CircleCrop()
        ROUND -> RoundedCorners(20)
        else -> {
            TODO()
        }
    }
}