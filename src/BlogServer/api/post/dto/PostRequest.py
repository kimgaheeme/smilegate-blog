from typing import Any

from pydantic import BaseModel
from fastapi import UploadFile, File
from enum import Enum


class PostType(str, Enum):
    plain = "plain"
    md = "md"


class CreatePostRequest(BaseModel):
    title: str
    content: str
    postImage: str

    class Config:
        orm_mode = True



class UpdatePostRequest(BaseModel):
    title: str = ""
    content: str = ""
    postImage: str = ""
    class Config:
        orm_mode = True