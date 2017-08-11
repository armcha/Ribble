package com.luseen.ribble.data.repository

import com.luseen.ribble.data.mapper.UserMapper
import com.luseen.ribble.data.network.TokenApiService
import com.luseen.ribble.data.network.UserApiService
import com.luseen.ribble.data.response.TokenResponse
import com.luseen.ribble.di.scope.PerUser
import com.luseen.ribble.domain.repository.UserRepository
import com.luseen.ribble.presentation.model.User
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerUser
class UserDataRepository @Inject constructor(
        private val tokenApiService: TokenApiService,
        private val userApiService: UserApiService,
        private val userMapper: UserMapper) : UserRepository {

    fun getToken(authCode: String): Flowable<TokenResponse> {
        return tokenApiService.getToken(authCode)
    }

    override fun getUser(): Flowable<User> {
        return userApiService.getUser().map {
            userMapper.translate(it)
        }
    }

}