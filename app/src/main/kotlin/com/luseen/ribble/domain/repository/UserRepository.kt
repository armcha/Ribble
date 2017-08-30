package com.luseen.ribble.domain.repository

import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.domain.entity.User
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Chatikyan on 10.08.2017.
 */
interface UserRepository {

    fun saveUserLoggedIn()

    fun saveUserLoggedOut()

    fun saveUserCode(code: String)

    fun isUserLoggedIn(): Boolean

    fun getUser(): Flowable<User>

    fun getUserCode(): String

    fun getUserLikes(count: Int): Single<List<Like>>

    fun getFollowing(count: Int): Single<List<Shot>>


}