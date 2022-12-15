package com.smilegateblog.smliegateblog.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.databinding.ItemPostBinding

class HomeRecentPostAdapter : PagingDataAdapter<GetRecentPostResponseItem, PagingViewHolder>(diffCallback) {
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
        private val diffCallback = object : DiffUtil.ItemCallback<GetRecentPostResponseItem>() {
            override fun areItemsTheSame(oldItem: GetRecentPostResponseItem, newItem: GetRecentPostResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GetRecentPostResponseItem, newItem: GetRecentPostResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class PagingViewHolder(
    private val binding: ItemPostBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(post: GetRecentPostResponseItem) {
        binding.labelPostContent.text = post.content
        binding.labelPostTitle.text = post.title
    }
}