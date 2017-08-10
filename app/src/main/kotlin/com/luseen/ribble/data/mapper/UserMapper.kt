package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.entity.UserEntity
import com.luseen.ribble.presentation.model.User

/**
 * Created by Chatikyan on 10.08.2017.
 */
class UserMapper {

    fun translate(userEntity: UserEntity): User {
        return User(userEntity.name, userEntity.avatarUrl, userEntity.username)
    }
}