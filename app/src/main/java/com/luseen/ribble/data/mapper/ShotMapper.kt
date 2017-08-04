package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.entity.ShotEntity
import com.luseen.ribble.presentation.model.Shot

/**
 * Created by Chatikyan on 03.08.2017.
 */
class ShotMapper {

    fun translate(shotEntityList : List<ShotEntity>):List<Shot>{
        return shotEntityList.map {
            Shot(it.title)
        }
    }
}