package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.repository.UserDataRepository
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.entity.User
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
                .flatMap { userDataRepository.getUser() }
    }

    fun logOut() {
        userDataRepository.logOut()
    }

    fun logIn() {
        userDataRepository.logIn()
    }

    fun getAuthenticatedUser(): Flowable<User> {
        return userDataRepository.getUser()
    }

    fun isUserLoggedIn(): Boolean = userDataRepository.isUserLoggedIn()

    fun getUserLikes(count: Int): Single<List<Like>> {
        return userDataRepository.getUserLikes(count)
    }

    fun getFollowing(count: Int): Single<List<Shot>> {
        return userDataRepository.getFollowing(count)
    }
}