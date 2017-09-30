package io.armcha.ribble.presentation.adapter

import android.view.View
import io.armcha.ribble.domain.entity.Comment
import io.armcha.ribble.presentation.utils.glide.TransformationType
import io.armcha.ribble.presentation.utils.glide.clear
import io.armcha.ribble.presentation.utils.glide.load
import io.armcha.ribble.R

import kotlinx.android.synthetic.main.comment_item.view.*

/**
 * Created by Chatikyan on 26.08.2017.
 */
class CommentRecyclerAdapter constructor(
        commentList: List<Comment>)
    : AbstractAdapter<Comment>(commentList, R.layout.comment_item) {

    override fun onViewRecycled(itemView: View) {
        itemView.userImage.clear()
    }

    override fun View.bind(item: Comment) {
        comment.text = item.commentText
        with(item) {
            commentAuthor.text = user?.username
            userImage.load(user?.avatarUrl, TransformationType.CIRCLE)
            userCommentLikeCount.text = item.likeCount.toString()
        }
    }
}