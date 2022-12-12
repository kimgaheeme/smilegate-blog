from pydantic import BaseModel


class CreatePostResponse(BaseModel):
    postId: int
