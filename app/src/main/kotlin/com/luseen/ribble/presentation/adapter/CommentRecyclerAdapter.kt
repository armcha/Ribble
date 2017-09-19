package com.luseen.ribble.presentation.adapter

import android.view.View
import android.view.ViewGroup
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.presentation.adapter.holder.CommentViewHolder
import com.luseen.ribble.utils.extensions.inflate
import com.luseen.ribble.utils.glide.clear
import kotlinx.android.synthetic.main.comment_item.view.*

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
        itemView.userImage.clear()
    }
}