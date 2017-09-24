package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.pref.Preferences
import com.luseen.ribble.data.repository.UserDataRepository
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.entity.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerActivity
class UserInteractor @Inject constructor(private val userDataRepository: UserDataRepository,
                                         private val preferences: Preferences) {

    fun getUser(code: String): Flowable<User> {
        return userDataRepository.getToken(code)
                .doOnNext { preferences saveUserToken it.token }
                .flatMap { userDataRepository.getUser() }
                .doOnNext { userDataRepository.logIn() }
    }

    fun logOut() {
        userDataRepository.logOut()
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

    fun follow(userName: String): Completable {
        return userDataRepository.follow(userName)
    }
}