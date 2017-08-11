package com.luseen.ribble.data.mapper

import com.luseen.ribble.data.response.UserResponse
import com.luseen.ribble.presentation.model.User

/**
 * Created by Chatikyan on 10.08.2017.
 */
class UserMapper {

    fun translate(userResponse: UserResponse): User {
        return User(userResponse.name, userResponse.avatarUrl, userResponse.username)
    }
}