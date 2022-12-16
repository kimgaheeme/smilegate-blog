package com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.databinding.ItemCommentBinding


class CommentAdapter : PagingDataAdapter<GetCommentsResponseItem, PagingViewHolder>(diffCallback) {
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

class PagingViewHolder(
    private val binding: ItemCommentBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(GetCommentsResponseItem: GetCommentsResponseItem) {
        binding.labelCommentContent.text = GetCommentsResponseItem.content
        binding.labelCommentNickname.text = GetCommentsResponseItem.nickname
    }
}