from pydantic import BaseModel
from fastapi import UploadFile, File
from enum import Enum


class PostType(str, Enum):
    plain = "plain"
    md = "md"


class CreatePostRequest(BaseModel):
    title: str = "a"
    content: str = ""
    postImage: str = ""


class UpdatePostRequest(BaseModel):
    title: str = ""
    content: str = ""
    postImage: str = ""
