package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.repository.UserDataRepository
import com.luseen.ribble.di.scope.PerUser
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerUser
class UserInteractor @Inject constructor(private val userDataRepository: UserDataRepository) {

    fun getToken(code: String): Flowable<String> {
        return userDataRepository.getToken(code)
    }
}