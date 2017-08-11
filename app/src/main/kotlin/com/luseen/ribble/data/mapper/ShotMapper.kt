package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.response.LikeResponse
import com.luseen.ribble.data.response.ShotResponse
import com.luseen.ribble.presentation.model.Like
import com.luseen.ribble.presentation.model.Shot

/**
 * Created by Chatikyan on 03.08.2017.
 */
class ShotMapper {

    @JvmName("translateShotEntity")
    fun translate(shotResponseList: List<ShotResponse>): List<Shot> {
        return shotResponseList.map {
            Shot(it.title, it.id)
        }
    }

    @JvmName("translateLikeEntity")
    fun translate(likeResponseList: List<LikeResponse>): List<Like> {
        return likeResponseList.map {
            Like(it.id)
        }
    }
}