from pydantic import BaseModel


class CreateCommentResponse(BaseModel):
    commentId: int