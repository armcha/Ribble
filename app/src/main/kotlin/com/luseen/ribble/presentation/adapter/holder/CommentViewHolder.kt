package com.luseen.ribble.presentation.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.utils.toHtml
import kotlinx.android.synthetic.main.comment_item.view.*

/**
 * Created by Chatikyan on 26.08.2017.
 */
class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(comment: Comment) {
        itemView.comment.text = comment.comment?.toHtml()
        itemView.commentAuthor.text = comment.user?.username
    }
}
