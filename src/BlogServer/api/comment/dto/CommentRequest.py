from pydantic import BaseModel



class CreateCommentRequest(BaseModel):
    content: str


class UpdateCommentRequest(BaseModel):
    content: str



