from fastapi import APIRouter, HTTPException, status

from api.model import User
from db_conn import sessionmaker
from _datetime import datetime
from api.user.dto.UserRequest import *
from api.user.dto.UserResponse import *

router = APIRouter()
now = datetime.now()


@router.post(
    "/users",
    response_model=CreateUserResponse,
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
    if sessionmaker.query(User).filter(User.email == userRequest.email).filter(User.nickname == userRequest.nickname).count() < 1:
        add_user = User(email=userRequest.email, nickname=userRequest.nickname, created_at=now.date())
        sessionmaker.add(add_user)
        sessionmaker.flush()
        sessionmaker.commit()
        return CreateUserResponse(userId=add_user.user_id, email=add_user.email, nickname=add_user.nickname)
    else:
        user_object = sessionmaker.query(User) \
            .filter(User.nickname == userRequest.nickname) \
            .filter(User.email == userRequest.email) \
            .first()
        return CreateUserResponse(userId=user_object.user_id, email=user_object.email, nickname=user_object.nickname)
        



@router.post(
    "/users/myinfo",
    response_model=GetUserInfoResponse,
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

    return_object = GetUserInfoResponse(email=user_object.email, nickname=user_object.nickname)
    return return_object
