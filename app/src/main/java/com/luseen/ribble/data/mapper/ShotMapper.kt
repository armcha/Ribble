package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.entity.LikeEntity
import com.luseen.ribble.data.entity.ShotEntity
import com.luseen.ribble.presentation.model.Like
import com.luseen.ribble.presentation.model.Shot

/**
 * Created by Chatikyan on 03.08.2017.
 */
class ShotMapper {

    @JvmName("translateShotEntity")
    fun translate(shotEntityList: List<ShotEntity>): List<Shot> {
        return shotEntityList.map {
            Shot(it.title)
        }
    }

    @JvmName("translateLikeEntity")
    fun translate(likeEntityList: List<LikeEntity>): List<Like> {
        return likeEntityList.map {
            Like(it.id)
        }
    }
}