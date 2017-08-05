package com.luseen.ribble.data.repository.data_store

import com.luseen.ribble.data.entity.LikeEntity
import com.luseen.ribble.data.entity.ShotEntity
import io.reactivex.Flowable

/**
 * Created by Chatikyan on 29.07.2017.
 */
interface ShotDataStore {

    fun getShotList(count:Int): Flowable<List<ShotEntity>>

    fun getShotLikes(shotId:String): Flowable<List<LikeEntity>>
}