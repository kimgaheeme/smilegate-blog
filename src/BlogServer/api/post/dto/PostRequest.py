from pydantic import BaseModel
from enum import Enum


class PostType(str, Enum):
    plain = "plain"
    md = "md"


class CreatePostRequest(BaseModel):
    title: str
    content: str
    postImage: str = ""
    type: PostType = PostType.plain


class UpdatePostRequest(BaseModel):
    title: str
    content: str
    postImage: str