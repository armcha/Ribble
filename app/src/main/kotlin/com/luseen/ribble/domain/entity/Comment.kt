package com.luseen.ribble.domain.entity

/**
 * Created by Chatikyan on 26.08.2017.
 */
data class Comment(val comment: String?,
                   val user: User?,
                   val likeCount: Int)