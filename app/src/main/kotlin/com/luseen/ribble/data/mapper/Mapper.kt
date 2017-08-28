package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.response.*
import com.luseen.ribble.domain.entity.*
import com.luseen.ribble.utils.emptyString

/**
 * Created by Chatikyan on 03.08.2017.
 */
class Mapper {

    @JvmName("translateShotEntity")
    fun translate(shotResponseList: List<ShotResponse>): List<Shot> {
        return shotResponseList.map {
            translate(it)
        }
    }

    @JvmName("translateLikeEntity")
    fun translate(likeResponseList: List<LikeResponse>): List<Like> {
        return likeResponseList.map {
            Like(it.id, translate(it.shotResponse))
        }
    }

    @JvmName("translateCommentEntity")
    fun translate(commentResponseList: List<CommentResponse>): List<Comment> {
        return commentResponseList.map {
            Comment(it.comment, translate(it.user))
        }
    }

    fun translate(userResponse: UserResponse?): User {
        return User(userResponse?.name, userResponse?.avatarUrl, userResponse?.username)
    }

    private fun translate(imageResponse: ImageResponse?): Image? {
        return imageResponse?.let {
            with(imageResponse) {
                Image(small ?: emptyString(),
                        normal ?: emptyString(),
                        big ?: emptyString())
            }
        }
    }

    private fun translate(shotResponse: ShotResponse?): Shot {
        return Shot(shotResponse?.title,
                shotResponse?.id,
                translate(shotResponse?.image),
                shotResponse?.description)
    }
}