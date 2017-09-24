package com.luseen.ribble.presentation.adapter

import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.presentation.utils.glide.TransformationType
import com.luseen.ribble.presentation.utils.glide.clear
import com.luseen.ribble.presentation.utils.glide.load
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