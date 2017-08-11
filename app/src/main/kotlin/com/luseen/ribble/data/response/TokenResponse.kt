package com.luseen.ribble.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Chatikyan on 10.08.2017.
 */
class TokenResponse {

    @SerializedName("access_token")
    @Expose
    val token: String? = null

    @SerializedName("token_type")
    @Expose
    val tokenType: String? = null

    @SerializedName("created_at")
    @Expose
    val createdAt: Long? = null
}