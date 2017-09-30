package io.armcha.ribble.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Chatikyan on 26.08.2017.
 */
class CommentResponse {

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("body")
    @Expose
    val comment: String? = null

    @SerializedName("likes_count")
    @Expose
    val likesCount: Int = 0

    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null

    @SerializedName("user")
    @Expose
    val user: UserResponse? = null

}