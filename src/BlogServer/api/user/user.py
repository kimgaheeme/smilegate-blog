from fastapi import APIRouter, HTTPException, status

from pydantic import BaseModel
from api.user.model.UserModel import User
from db_conn import sessionmaker
from _datetime import datetime

router = APIRouter()
now = datetime.now()


class CreateUserRequest(BaseModel):
    nickname: str
    email: str


@router.post(
    "/users",
    responses={
        status.HTTP_401_UNAUTHORIZED: {
            "description": "10001.",
        },
        status.HTTP_403_FORBIDDEN: {
            "description": "10002.",
        },
        status.HTTP_404_NOT_FOUND: {
            "description": "10003.",
        },
    }
)
async def create_user(userRequest: CreateUserRequest):
    if sessionmaker.query(User).filter(User.email == userRequest.email).count() >= 1:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND)
    else:
        sessionmaker.add(User(email=userRequest.email, nickname=userRequest.nickname, created_at=now.date()))
        sessionmaker.commit()

        user_object = sessionmaker.query(User) \
            .filter(User.email == userRequest.email) \
            .filter(User.nickname == userRequest.nickname).first()

        return user_object


@router.post(
    "/users/myinfo",
    responses={
        status.HTTP_401_UNAUTHORIZED: {
            "description": "10001.",
        },
        status.HTTP_403_FORBIDDEN: {
            "description": "10002.",
        },
        status.HTTP_404_NOT_FOUND: {
            "description": "10003.",
        },
    }
)
async def get_user_info(userId: int):
    user_object = sessionmaker.query(User) \
        .filter(User.user_id == userId) \
        .first()

    return user_object
