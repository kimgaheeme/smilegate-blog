from pydantic import BaseModel


class CreateScrapResponse(BaseModel):
    scrapId: int


class GetScrapPostResponse(BaseModel):
    title: str
    content: str
    postImageId: str
    postId: int
