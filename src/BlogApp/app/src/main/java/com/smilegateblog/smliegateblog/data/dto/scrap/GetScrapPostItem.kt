package com.smilegateblog.smliegateblog.data.dto.scrap


import com.google.gson.annotations.SerializedName

data class GetScrapPostItem(
    val content: String,
    val postImageId: String,
    val title: String,
    val postId: Int
)