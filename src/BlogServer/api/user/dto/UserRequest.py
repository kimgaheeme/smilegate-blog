from pydantic import BaseModel


class CreateUserRequest(BaseModel):
    nickname: str
    email: str

