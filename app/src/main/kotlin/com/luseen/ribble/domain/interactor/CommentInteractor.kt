package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.repository.ShotDataRepository
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Comment
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 26.08.2017.
 */
@PerActivity
class CommentInteractor @Inject constructor(private val shotRepository: ShotDataRepository)  {

    fun getComments(shotId: String): Single<List<Comment>> {
        return shotRepository.getShotComments(shotId)
    }
}