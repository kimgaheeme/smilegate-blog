from pydantic import BaseModel


class CreateUserResponse(BaseModel):
    userId: int
    email: str
    nickname: str


class GetUserInfoResponse(BaseModel):
    email: str
    nickname: str
