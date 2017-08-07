package com.luseen.ribble.domain.repository

import com.luseen.ribble.presentation.model.Like
import com.luseen.ribble.presentation.model.Shot
import io.reactivex.Flowable

/**
 * Created by Chatikyan on 02.08.2017.
 */
interface ShotRepository {

    fun getShotList(shotType:String,count:Int): Flowable<List<Shot>>

    fun getShotLikes(shotId:String): Flowable<List<Like>>

    fun getSomeDataFromPref(): Boolean
}