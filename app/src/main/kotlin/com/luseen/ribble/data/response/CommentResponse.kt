package com.luseen.ribble.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Chatikyan on 26.08.2017.
 */
class CommentResponse {

//        "id": 6497629,
//        "body": "<p>Welcome to the game! Great job :)</p>",
//        "likes_count": 1,
//        "likes_url": "https://api.dribbble.com/v1/shots/3761078/comments/6497629/likes",
//        "created_at": "2017-08-25T11:29:35Z",
//        "updated_at": "2017-08-25T11:29:59Z",

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