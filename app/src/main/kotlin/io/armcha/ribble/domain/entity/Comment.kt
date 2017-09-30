package io.armcha.ribble.domain.entity

import android.text.Spanned
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Chatikyan on 26.08.2017.
 */
data class Comment(val commentText: Spanned?,
                   val user: User?,
                   val likeCount: Int,
                   private val date: Date?) {

    val commentDate = "${format("dd")} ${format("MMM")} ${format("HH:mm")}"
    private fun format(pattern: String) = SimpleDateFormat(pattern, Locale.ENGLISH).format(date)
}