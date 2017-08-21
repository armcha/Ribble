package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.response.LikeResponse
import com.luseen.ribble.data.response.ShotResponse
import com.luseen.ribble.data.response.UserResponse
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.entity.User

/**
 * Created by Chatikyan on 03.08.2017.
 */
class Mapper {

    @JvmName("translateShotEntity")
    fun translate(shotResponseList: List<ShotResponse>): List<Shot> {
        return shotResponseList.map {
            Shot(it.title, it.id)
        }
    }

    @JvmName("translateLikeEntity")
    fun translate(likeResponseList: List<LikeResponse>): List<Like> {
        return likeResponseList.map {
            Like(it.id, translate(it.shotResponse))
        }
    }

    fun translate(shotResponse: ShotResponse?): Shot {
        return Shot(shotResponse?.title, shotResponse?.id)
    }

    fun translate(userResponse: UserResponse): User {
        return User(userResponse.name, userResponse.avatarUrl, userResponse.username)
    }
}