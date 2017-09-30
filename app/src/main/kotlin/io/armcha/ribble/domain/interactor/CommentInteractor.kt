package io.armcha.ribble.domain.interactor

import io.armcha.ribble.data.cache.MemoryCache
import io.armcha.ribble.data.repository.ShotDataRepository
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Comment
import io.armcha.ribble.domain.fetcher.result_listener.RequestType
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 26.08.2017.
 */
@PerActivity
class CommentInteractor @Inject constructor(private val shotRepository: ShotDataRepository,
                                            private val memoryCache: MemoryCache) {

    fun getComments(shotId: String): Single<List<Comment>> {
        return shotRepository.getShotComments(shotId)
    }

    fun getCommentsFromMemory(): List<Comment> = memoryCache getCacheForType RequestType.COMMENTS
}