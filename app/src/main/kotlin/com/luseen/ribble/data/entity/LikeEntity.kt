package com.luseen.ribble.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Chatikyan on 05.08.2017.
 */
class LikeEntity {

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null
}