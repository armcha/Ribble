package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.cache.MemoryCache
import com.luseen.ribble.data.repository.ShotDataRepository
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.domain.fetcher.result_listener.RequestType
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