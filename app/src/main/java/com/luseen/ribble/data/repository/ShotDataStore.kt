package com.luseen.ribble.data.repository

import com.luseen.ribble.data.entity.Shot
import io.reactivex.Observable

/**
 * Created by Chatikyan on 29.07.2017.
 */
interface ShotDataStore {

    fun getShotList(): Observable<List<Shot>>
}