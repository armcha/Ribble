package io.armcha.ribble.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Chatikyan on 28.08.2017.
 */
 class ImageResponse{

    @SerializedName("hidpi")
    @Expose
    val big: String? = null

    @SerializedName("normal")
    @Expose
    val normal: String? = null

    @SerializedName("teaser")
    @Expose
    val small: String? = null
}