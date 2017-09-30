package io.armcha.ribble.data.repository

import io.armcha.ribble.data.mapper.Mapper
import io.armcha.ribble.data.network.AuthApiService
import io.armcha.ribble.data.network.UserApiService
import io.armcha.ribble.data.pref.Preferences
import io.armcha.ribble.data.response.TokenResponse
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.entity.User
import io.armcha.ribble.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerActivity
class UserDataRepository @Inject constructor(
        private val authApiService: AuthApiService,
        private val userApiService: UserApiService,
        private var preferences: Preferences,
        private val mapper: Mapper) : UserRepository {

    fun getToken(authCode: String): Flowable<TokenResponse> {
        return authApiService.getToken(authCode)
    }

    override fun getUser(): Flowable<User> {
        return userApiService.getUser().map {
            mapper.translate(it)
        }
    }

    override fun logIn() {
        preferences.saveUserLoggedIn()
    }

    override fun logOut() {
        preferences.saveUserLoggedOut()
        preferences.deleteToken()
    }

    override fun isUserLoggedIn(): Boolean {
        return preferences.isUserLoggedIn
    }

    override fun getUserLikes(count: Int): Single<List<Like>> {
        return userApiService.getUserLikes(pageSize = count)
                .map { mapper.translate(it) }
    }

    override fun getFollowing(count: Int): Single<List<Shot>> {
        return userApiService.getFollowing(count)
                .map { mapper.translate(it) }
    }

    override fun follow(userName: String): Completable {
        return userApiService.follow(userName)
    }
}