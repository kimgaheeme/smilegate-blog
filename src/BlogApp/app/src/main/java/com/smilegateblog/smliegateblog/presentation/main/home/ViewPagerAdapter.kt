package com.smilegateblog.smliegateblog.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smilegateblog.smliegateblog.R
import com.smilegateblog.smliegateblog.data.dto.post.GetMostViewedResponseItem
import com.smilegateblog.smliegateblog.databinding.ItemMostviewedBinding
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.util.setImageUrl

class PagerViewHolder(val binding: ItemMostviewedBinding) : RecyclerView.ViewHolder(binding.root)

class ViewPagerAdapter(var data: List<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = PagerViewHolder(
        ItemMostviewedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as PagerViewHolder).binding

        binding.tvMostviewedContent.setText(data[position].content)
        binding.tvMostviewedTitle.setText(data[position].title)
        binding.tvMostviewedNickname.setText(data[position].nickname)
        binding.ivMostviewedPostImage.setImageUrl(data[position].postImageId, null)
    }
}