package com.luseen.ribble.data.repository.data_store

import com.luseen.ribble.data.db.DbHelper
import com.luseen.ribble.data.entity.LikeEntity
import com.luseen.ribble.data.entity.ShotEntity
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Singleton
class LocaleDataStore @Inject constructor() : ShotDataStore {

    private var dbHelper: DbHelper? = null

    override fun getShotList(count: Int): Flowable<List<ShotEntity>> {
        null!!
    }

    override fun getShotLikes(shotId: String): Flowable<List<LikeEntity>> {
        throw UnsupportedOperationException("Always get likes from Api")
    }
}