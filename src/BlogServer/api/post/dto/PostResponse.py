from pydantic import BaseModel
from _datetime import datetime
from api.post.dto.PostRequest import PostType


class CreatePostResponse(BaseModel):
    postId: int


class UpdatePostResponse(BaseModel):
    postId: int


class GetRecentPostResponse(BaseModel):
    title: str
    content: str
    postImageId: str
    created_at: datetime
    postId: int


class GetMostViewedPostResponse(BaseModel):
    title: str
    content: str
    postImageId: str
    nickname: str
    postId: int


class GetMyPostResponse(BaseModel):
    title: str
    content: str
    postImageId: str
    postId: int


class GetPostDetailResponse(BaseModel):
    nickname: str
    title: str
    content: str
    postImageId: str
    type: PostType
    view_cnt: int
    update_at: datetime
    isScrap: bool
