from pydantic import BaseModel


class CreateCommentRequest(BaseModel):
    content: str
