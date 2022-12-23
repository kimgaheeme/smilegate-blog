package com.smilegateblog.smliegateblog.domain.model


import com.google.gson.annotations.SerializedName
import com.smilegateblog.smliegateblog.data.dto.post.GetMostViewedResponseItem
import com.smilegateblog.smliegateblog.data.dto.post.GetMyPostResponseItem
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem

data class Post(
    var content: String,
    @SerializedName("created_at")
    val createdAt: String = "",
    val nickname: String = "",
    val postId: Int,
    val postImageId: String,
    val title: String
)

fun GetRecentPostResponseItem.toDomain(): Post = Post(
    content = this.content,
    createdAt = this.createdAt,
    postId = this.postId,
    postImageId = this.postImageId,
    title = this.title
)

fun GetMostViewedResponseItem.toDomain(): Post = Post(
    content = this.content,
    nickname = this.nickname,
    postId = this.postId,
    postImageId = this.postImageId,
    title = this.title
)

fun GetMyPostResponseItem.toDomain(): Post = Post(
    content = this.content,
    postId = this.postId,
    postImageId = this.postImageId,
    title = this.title
)

fun GetScrapPostItem.toDomain(): Post = Post(
    content = this.content,
    postId = this.postId,
    postImageId = this.postImageId,
    title = this.title
)