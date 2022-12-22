from typing import Any

from pydantic import BaseModel
from fastapi import UploadFile, File
from enum import Enum


class PostType(str, Enum):
    plain = "plain"
    md = "md"


class CreatePostRequest(BaseModel):
    title: Any
    content: Any
    postImage: Any

    class Config:
        orm_mode = True



class UpdatePostRequest(BaseModel):
    title: str = ""
    content: str = ""
    postImage: str = ""