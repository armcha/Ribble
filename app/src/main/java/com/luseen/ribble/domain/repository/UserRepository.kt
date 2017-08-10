package com.luseen.ribble.domain.repository

import io.reactivex.Flowable

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface UserRepository {

    fun getToken(authCode: String): Flowable<String>

}