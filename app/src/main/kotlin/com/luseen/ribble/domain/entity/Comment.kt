package com.luseen.ribble.domain.entity

import com.luseen.ribble.utils.extensions.emptyString
import com.luseen.ribble.utils.extensions.toHtml
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Chatikyan on 26.08.2017.
 */
data class Comment(private val comment: String?,
                   val user: User?,
                   val likeCount: Int,
                   private val date: String?) {

    private var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    private var createDate: Date = format.parse(date)
    val commentDate = "${format("dd")} ${format("MMM")} ${format("HH:mm")}"
    val commentText = comment?.toHtml() ?: emptyString
    private fun format(pattern: String) = SimpleDateFormat(pattern, Locale.ENGLISH).format(createDate)
}