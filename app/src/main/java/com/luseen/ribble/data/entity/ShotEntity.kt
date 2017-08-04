package com.luseen.ribble.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Chatikyan on 30.07.2017.
 */
class ShotEntity {

    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
}