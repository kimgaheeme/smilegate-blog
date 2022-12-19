package com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail

import android.content.Context
import android.content.res.Resources
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smilegateblog.smliegateblog.R
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.databinding.ItemCommentBinding
import com.smilegateblog.smliegateblog.presentation.common.visible
import com.smilegateblog.smliegateblog.presentation.main.home.OnItemClickListener

interface OnCommentClickListener<T> {
    fun onDeleteCommentClicked(item: T?)
}


class CommentAdapter(private val listener: OnCommentClickListener<Int>?) :
    PagingDataAdapter<GetCommentsResponseItem, CommentAdapter.PagingViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagingViewHolder(
            ItemCommentBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class PagingViewHolder(
        private val binding: ItemCommentBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(GetCommentsResponseItem: GetCommentsResponseItem) {
            binding.labelCommentContent.text = GetCommentsResponseItem.content
            binding.labelCommentNickname.text = GetCommentsResponseItem.nickname
            binding.btnDeleteComment.setOnClickListener {
                listener?.onDeleteCommentClicked(GetCommentsResponseItem.commentId)
                binding.labelCommentContent.text = "삭제된 댓글 입니다."
            }

        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<GetCommentsResponseItem>() {
            override fun areItemsTheSame(oldItem: GetCommentsResponseItem, newItem: GetCommentsResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GetCommentsResponseItem, newItem: GetCommentsResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}

