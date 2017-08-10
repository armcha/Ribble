package com.luseen.ribble.data.repository

import com.luseen.ribble.data.network.UserApiService
import com.luseen.ribble.di.scope.PerUser
import com.luseen.ribble.domain.repository.UserRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerUser
class UserDataRepository @Inject constructor(private val userApiService: UserApiService) : UserRepository {

    override fun getToken(authCode: String): Flowable<String> {
        return userApiService.getToken(authCode)
    }
}