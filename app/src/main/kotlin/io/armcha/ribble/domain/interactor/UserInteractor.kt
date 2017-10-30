package io.armcha.ribble.domain.interactor

import io.armcha.ribble.data.pref.Preferences
import io.armcha.ribble.data.repository.UserDataRepository
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.domain.entity.User
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
        userDataRepository.clearCache()
        userDataRepository.logOut()
    }

    fun getAuthenticatedUser(): Flowable<User> = userDataRepository.getUser()

    fun isUserLoggedIn(): Boolean = userDataRepository.isUserLoggedIn()

    fun getUserLikes(count: Int): Single<List<Like>> = userDataRepository.getUserLikes(count)

    fun getFollowing(count: Int) = userDataRepository.getFollowing(count)

    fun follow(userName: String) = userDataRepository.follow(userName)
}