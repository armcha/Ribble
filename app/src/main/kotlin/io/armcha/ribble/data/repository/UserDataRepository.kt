package io.armcha.ribble.data.repository

import io.armcha.ribble.data.cache.MemoryCache
import io.armcha.ribble.data.mapper.Mapper
import io.armcha.ribble.data.network.AuthApiService
import io.armcha.ribble.data.network.UserApiService
import io.armcha.ribble.data.pref.Preferences
import io.armcha.ribble.data.response.TokenResponse
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.domain.entity.User
import io.armcha.ribble.domain.fetcher.result_listener.RequestType
import io.armcha.ribble.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Chatikyan on 10.08.2017.
 */
class UserDataRepository(
        private val authApiService: AuthApiService,
        private val userApiService: UserApiService,
        private var preferences: Preferences,
        private val memoryCache: MemoryCache,
        private val mapper: Mapper) : UserRepository {

    override fun getToken(authCode: String): Flowable<TokenResponse> = authApiService.getToken(authCode)

    override fun getUser(): Flowable<User> {
        return userApiService.getUser().map {
            mapper.translate(it)
        }
    }

    override fun logIn() {
        preferences.saveUserLoggedIn()
    }

    override fun clearLoginData() {
        preferences.saveUserLoggedOut()
        preferences.deleteToken()
    }

    override fun isUserLoggedIn() = preferences.isUserLoggedIn

    override fun getUserLikes(count: Int): Single<List<Like>> {
        val requestType = RequestType.LIKED_SHOTS
        return if (memoryCache.hasCacheFor(requestType)) {
            Single.fromCallable<List<Like>> { memoryCache.getCacheForType(requestType) }
        } else {
            userApiService.getUserLikes(pageSize = count)
                    .map { mapper.translate(it) }
                    .doOnSuccess { memoryCache.put(requestType, it) }
        }
    }

    override fun getFollowing(count: Int): Single<List<Shot>> {
        val requestType = RequestType.FOLLOWINGS_SHOTS
        return if (memoryCache.hasCacheFor(requestType)) {
            Single.fromCallable<List<Shot>> { memoryCache.getCacheForType(requestType) }
        } else {
            userApiService.getFollowing(pageSize = count)
                    .map { mapper.translate(it) }
                    .doOnSuccess { memoryCache.put(requestType, it) }
        }
    }

    override fun follow(userName: String) = userApiService.follow(userName)

    override fun clearCache() {
        memoryCache.clearCache()
    }
}