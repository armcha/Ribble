package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.response.*
import com.luseen.ribble.domain.entity.*
import com.luseen.ribble.presentation.utils.extensions.emptyString
import com.luseen.ribble.presentation.utils.extensions.toHtml
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Chatikyan on 03.08.2017.
 */
class Mapper {

    @JvmName("translateShotEntity")
    fun translate(shotResponseList: List<ShotResponse>): List<Shot> {
        return shotResponseList
                .asSequence()
                .map {
                    translate(it)
                }
                .toList()
    }

    @JvmName("translateLikeEntity")
    fun translate(likeResponseList: List<LikeResponse>): List<Like> {
        return likeResponseList
                .asSequence()
                .map {
                    Like(it.id, translate(it.shotResponse))
                }
                .toList()
    }

    @JvmName("translateCommentEntity")
    fun translate(commentResponseList: List<CommentResponse>): List<Comment> {
        return commentResponseList
                .asSequence()
                .map {
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
                    Comment(it.comment?.toHtml(), translate(it.user), it.likesCount, format.parse(it.createdAt))
                }
                .toList()
    }

    fun translate(userResponse: UserResponse?): User {
        return User(userResponse?.name,
                userResponse?.avatarUrl ?: emptyString,
                userResponse?.username,
                userResponse?.location ?: emptyString)
    }

    private fun translate(imageResponse: ImageResponse?): Image {
        return imageResponse.let {
            Image(imageResponse?.small ?: emptyString,
                    imageResponse?.normal ?: emptyString,
                    imageResponse?.big ?: emptyString)
        }
    }

    private fun translate(shotResponse: ShotResponse?): Shot {
        return Shot(shotResponse?.title,
                shotResponse?.id,
                translate(shotResponse?.image),
                translate(shotResponse?.user),
                shotResponse?.description,
                shotResponse?.likesCount,
                shotResponse?.viewsCount,
                shotResponse?.bucketsCount)
    }
}