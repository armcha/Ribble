package com.luseen.ribble.presentation.adapter

import android.view.View
import android.view.ViewGroup
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.presentation.adapter.holder.CommentViewHolder
import com.luseen.ribble.utils.inflate

/**
 * Created by Chatikyan on 26.08.2017.
 */
class CommentRecyclerAdapter constructor(
        commentList: List<Comment>)
    : AbstractAdapter<CommentViewHolder, Comment>(commentList) {

    override fun onBind(holder: CommentViewHolder, item: Comment) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): CommentViewHolder {
        val view = parent inflate R.layout.comment_item
        return CommentViewHolder(view)
    }

    override fun onViewRecycled(itemView: View) {

    }
}