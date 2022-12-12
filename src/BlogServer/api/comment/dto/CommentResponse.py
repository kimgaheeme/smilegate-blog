from pydantic import BaseModel


class CreateCommentResponse(BaseModel):
    commentId: int


class UpdateCommentResponse(BaseModel):
    commentId: int
