package com.smilegateblog.smliegateblog.data.dto.post


import com.google.gson.annotations.SerializedName

data class GetRecentPostResponseItem(
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    val postImageId: String,
    val title: String,
    val postId: Int
)