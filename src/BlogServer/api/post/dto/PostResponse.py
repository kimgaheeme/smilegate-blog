from pydantic import BaseModel


class CreatePostResponse(BaseModel):
    postId: int


class UpdatePostResponse(BaseModel):
    postId: int
