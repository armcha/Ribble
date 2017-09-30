package io.armcha.ribble.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Chatikyan on 10.08.2017.
 */
class UserResponse {

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("username")
    @Expose
    val username: String? = null

    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String? = null

    @SerializedName("bio")
    @Expose
    val bio: String? = null

    @SerializedName("followers_count")
    @Expose
    val followersCount: Int? = null

    @SerializedName("followings_count")
    @Expose
    val followingsCount: Int? = null

    @SerializedName("likes_count")
    @Expose
    val likesCount: Int? = null

    @SerializedName("location")
    @Expose
    val location: String? = null
}

