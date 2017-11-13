package io.armcha.ribble.domain.interactor

import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Comment
import io.armcha.ribble.domain.fetcher.result_listener.RequestType
import io.armcha.ribble.domain.repository.ShotRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 26.08.2017.
 */
@PerActivity
class CommentInteractor @Inject constructor(private val shotRepository: ShotRepository) {

    fun getComments(shotId: String) = shotRepository.getShotComments(shotId)

    fun deleteCommentsCache() {
        shotRepository.deleteCacheFor(RequestType.COMMENTS)
    }
}