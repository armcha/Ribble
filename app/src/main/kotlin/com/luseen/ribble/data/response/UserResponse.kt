package com.luseen.ribble.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Chatikyan on 10.08.2017.
 */
class UserResponse {

    //TODO delete
    // id=1835830.0, name=Arman Chatikyan, username=ArmanChatikyan, html_url=https://dribbble.com/ArmanChatikyan,
    // avatar_url=https://cdn.dribbble.com/assets/avatar-default-aa2eab7684294781f93bc99ad394a0eb3249c5768c21390163c9f55ea8ef83a4.gif,
    // bio=, location=null, links={twitter=https://twitter.com/ArmanChatikyan}, buckets_count=0.0, comments_received_count=0.0,
    // followers_count=0.0, followings_count=0.0, likes_count=1.0, likes_received_count=0.0, projects_count=0.0,
    // rebounds_received_count=0.0, shots_count=0.0, teams_count=0.0, can_upload_shot=false, type=User, pro=false,
    // buckets_url=https://api.dribbble.com/v1/users/1835830/buckets, followers_url=https://api.dribbble.com/v1/users/1835830/followers,
    // following_url=https://api.dribbble.com/v1/users/1835830/following, likes_url=https://api.dribbble.com/v1/users/1835830/likes,
    // projects_url=https://api.dribbble.com/v1/users/1835830/projects, shots_url=https://api.dribbble.com/v1/users/1835830/shots,
    // teams_url=https://api.dribbble.com/v1/users/1835830/teams, created_at=2017-07-26T16:48:44Z, updated_at=2017-07-26T16:50:39Z}

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

