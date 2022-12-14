package com.smilegateblog.smliegateblog.data.dto.post

data class GetPostResponse(
    val nickname:  String,
    val title :   String ,
    val content :   String ,
    val postImageId :   String ,
    val type :  String ,
    val view_cnt : Int,
    val update_at :  String,
    val isScrap : Boolean
)
