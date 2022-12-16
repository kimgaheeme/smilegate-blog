package com.smilegateblog.smliegateblog.data.dto.post


import com.google.gson.annotations.SerializedName

data class GetMostViewedResponseItem(
    val content: String,
    val nickname: String,
    val postImageId: String,
    val title: String,
    val postId: Int
)