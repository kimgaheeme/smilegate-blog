from pydantic import BaseModel
from _datetime import datetime


class CreateCommentResponse(BaseModel):
    commentId: int


class UpdateCommentResponse(BaseModel):
    commentId: int


class GetCommentResponse(BaseModel):
    nickname: str
    commentId: int
    content: str
    update_at: datetime

