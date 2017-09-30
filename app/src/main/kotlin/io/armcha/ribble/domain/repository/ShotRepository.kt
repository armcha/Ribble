package io.armcha.ribble.domain.repository

import io.armcha.ribble.domain.entity.Comment
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.domain.entity.Shot
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Chatikyan on 02.08.2017.
 */
interface ShotRepository {

    fun getShotList(shotType: String, count: Int): Flowable<List<Shot>>

    fun getShotLikes(shotId: String): Flowable<List<Like>>

    fun getShotComments(shotId: String): Single<List<Comment>>

    fun likeShot(shotId: String): Completable
}