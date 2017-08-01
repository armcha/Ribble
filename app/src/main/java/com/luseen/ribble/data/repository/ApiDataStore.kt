package com.luseen.ribble.data.repository

import com.luseen.ribble.data.entity.Shot
import io.reactivex.Observable

/**
 * Created by Chatikyan on 29.07.2017.
 */
class ApiDataStore : ShotDataStore {

    override fun getShotList(): Observable<List<Shot>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}