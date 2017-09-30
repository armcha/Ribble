package io.armcha.ribble.data.repository

import io.armcha.ribble.data.mapper.Mapper
import io.armcha.ribble.data.network.ShotApiService
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.repository.ShotRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 02.08.2017.
 */
@Singleton
class ShotDataRepository @Inject constructor(private var shotApiService: ShotApiService,
                                             private var mapper: Mapper) : ShotRepository {

    override fun getShotList(shotType: String, count: Int): Flowable<List<Shot>> =
            shotApiService.getShots(shotType, count)
                    .map { mapper.translate(it) }

    override fun getShotLikes(shotId: String): Flowable<List<Like>> {
        return shotApiService.getShotLikes(shotId).map {
            mapper.translate(it)
        }
    }

    override fun getShotComments(shotId: String) = shotApiService.getShotComments(shotId)
            .map { mapper.translate(it) }

    override fun likeShot(shotId: String) = shotApiService.likeShot(shotId)
}