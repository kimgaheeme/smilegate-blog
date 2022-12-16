package com.smilegateblog.smliegateblog.presentation.main.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.databinding.ItemPostBinding


interface OnItemClickListener<T> {
    fun onItemClicked(item: T?)
}

class HomeRecentPostAdapter(private val listener: OnItemClickListener<String>?) :
    PagingDataAdapter<GetRecentPostResponseItem, HomeRecentPostAdapter.PagingViewHolder>(diffCallback) {
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

        fun bind(post: GetRecentPostResponseItem) {
            binding.labelPostContent.text = post.content
            binding.labelPostTitle.text = post.title
            binding.root.setOnClickListener {
                listener?.onItemClicked(post.title)
            }
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
