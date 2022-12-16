from typing import List

from fastapi import APIRouter, HTTPException, status

from api.model import Post
from api.model import User
from api.model import Scrap
from db_conn import sessionmaker
from _datetime import datetime
from api.scrap.dto.ScrapRequest import *
from api.scrap.dto.ScrapResponse import *

router = APIRouter()
now = datetime.now()


@router.post(
    "/posts/scrap/{postid}",
    response_model=CreateScrapResponse,
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
async def create_scrap(userId: int, postid: int):
    if sessionmaker.query(Scrap).filter(Scrap.post_id == postid) \
            .filter(Scrap.user_id == userId).count() >= 1:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN)
    else:
        add_scrap = Scrap(
            user_id=userId,
            post_id=postid,
            created_at=now.date())

        sessionmaker.add(add_scrap)
        sessionmaker.flush()
        sessionmaker.commit()

        return CreateScrapResponse(scrapId=add_scrap.post_scrap_id)


@router.delete(
    "/posts/scrap/{postid}",
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
async def delete_scrap(userId: int, postid: int):
    if sessionmaker.query(Scrap).filter(Scrap.post_id == postid) \
            .filter(Scrap.user_id == userId).count() == 0:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN)
    else:

        sessionmaker.query(Scrap).filter(Scrap.user_id == userId).filter(Scrap.post_id == postid).delete()
        sessionmaker.commit()

        return None


@router.get(
    "/posts/scrap/my",
    response_model=List[GetScrapPostResponse],
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
async def get_scrap_post(userid: int, page: int = 1):
    offset = (page - 1) * 10
    posts = sessionmaker.query(Post) \
        .join(Scrap, Scrap.post_id == Post.post_id).order_by(Scrap.created_at) \
        .filter(Scrap.user_id == userid).offset(offset).limit(10).all()
    result = []

    for post in posts:
        result.append(GetScrapPostResponse(
            title=post.title,
            content=post.content,
            postImageId=post.post_image_id,
            postId=post.post_id
        ))

    return result
