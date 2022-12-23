package com.smilegateblog.smliegateblog.data.dto.comment


import com.google.gson.annotations.SerializedName

data class GetCommentsResponseItem(
    val commentId: Int,
    val content: String,
    val nickname: String,
    @SerializedName("update_at")
    val updateAt: String
)