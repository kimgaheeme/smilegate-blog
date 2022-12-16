package com.smilegateblog.smliegateblog.presentation.main.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smilegateblog.smliegateblog.databinding.ItemPostBinding
import com.smilegateblog.smliegateblog.domain.model.Post

class MyPostAdapter : PagingDataAdapter<Post, PagingViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagingViewHolder(
            ItemPostBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class PagingViewHolder(
    private val binding: ItemPostBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.labelPostContent.text = post.content
        binding.labelPostTitle.text = post.title
    }
}