package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.repository.UserDataRepository
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.entity.User
import com.luseen.ribble.utils.log
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerActivity
class UserInteractor @Inject constructor(private val userDataRepository: UserDataRepository) {

    fun getUser(code: String): Flowable<User> {
        return userDataRepository.getToken(code)
                .flatMap {
                    log { it.token }
                    userDataRepository.getUser()
                }
    }

    fun saveUserLoggedIn() {
        userDataRepository.saveUserLoggedIn()
    }

    fun saveUserLoggedOut() {
        userDataRepository.saveUserLoggedOut()
    }

    fun isUserLoggedIn(): Boolean = userDataRepository.isUserLoggedIn()

    fun getUserLikes(count: Int): Single<List<Like>> {
        return userDataRepository.getUserLikes(count)
    }
}