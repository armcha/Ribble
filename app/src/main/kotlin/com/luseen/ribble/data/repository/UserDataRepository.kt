package com.luseen.ribble.data.repository

import com.luseen.ribble.data.mapper.Mapper
import com.luseen.ribble.data.network.AuthApiService
import com.luseen.ribble.data.network.UserApiService
import com.luseen.ribble.data.pref.Preferences
import com.luseen.ribble.data.response.TokenResponse
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.entity.User
import com.luseen.ribble.domain.repository.UserRepository
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

    override fun saveUserLoggedIn() {
        preferences saveUserLoggedIn true
    }

    override fun isUserLoggedIn(): Boolean {
        return preferences.isUserLoggedIn()
    }

    override fun saveUserLoggedOut() {
        preferences saveUserLoggedIn false
    }

    override fun getUserLikes(count: Int): Single<List<Like>> {
        return userApiService.getUserLikes(pageSize = count)
                .map { mapper.translate(it) }
    }
}