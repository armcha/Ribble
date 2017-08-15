package com.luseen.ribble.domain.repository

import com.luseen.ribble.presentation.model.Like
import com.luseen.ribble.presentation.model.User
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface UserRepository {

    fun saveUserLoggedIn()

    fun saveUserLoggedOut()

    fun isUserLoggedIn(): Boolean

    fun getUser(): Flowable<User>

    fun getUserLikes(count: Int): Single<List<Like>>
}