package com.smilegateblog.smliegateblog.data.dto.post


import com.google.gson.annotations.SerializedName

data class GetMyPostResponseItem(
    val content: String,
    val postImageId: String,
    val title: String,
    val postId: Int
)