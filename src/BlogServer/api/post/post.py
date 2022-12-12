from fastapi import APIRouter, HTTPException, status

from api.model import Post
from db_conn import sessionmaker
from _datetime import datetime
from api.post.dto.PostRequest import *
from api.post.dto.PostResponse import *

router = APIRouter()
now = datetime.now()


@router.post(
    "/posts",
    response_model=CreatePostResponse,
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
async def create_post(userRequest: CreatePostRequest, userId: int):
    add_post = Post(
        user_id=userId,
        title=userRequest.title,
        content=userRequest.content,
        type=userRequest.type,
        update_at=now.date(),
        created_at=now.date())

    if userRequest.postImage is not None:
        add_post.post_image_id = userRequest.postImage
    else:
        add_post.post_image_id = "랜덤 이미지 넣기"

    sessionmaker.add(add_post)
    sessionmaker.flush()
    sessionmaker.commit()

    return CreatePostResponse(postId=add_post.post_id)
