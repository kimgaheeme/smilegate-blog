from pydantic import BaseModel
from _datetime import datetime


class CreatePostResponse(BaseModel):
    postId: int


class UpdatePostResponse(BaseModel):
    postId: int


class GetRecentPostResponse(BaseModel):
    title: str
    content: str
    postImageId: str
    created_at: datetime
