package com.smilegateblog.smliegateblog.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smilegateblog.smliegateblog.databinding.ItemPostBinding
import com.smilegateblog.smliegateblog.domain.model.Post


interface OnItemClickListener<T> {
    fun onItemClicked(item: T?)
}

class PostAdapter(private val listener: OnItemClickListener<String>?) :
    PagingDataAdapter<Post, PostAdapter.PagingViewHolder>(diffCallback) {
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

    inner class PagingViewHolder(
        private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.labelPostContent.text = post.content
            binding.labelPostTitle.text = post.title
            binding.root.setOnClickListener {
                listener?.onItemClicked(post.title)
            }
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
