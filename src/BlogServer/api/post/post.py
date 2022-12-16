from typing import List

from fastapi import APIRouter, HTTPException, status

from api.model import Post
from api.model import User
from api.model import Scrap
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
    },
    tags="Post"
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


@router.put(
    "/posts/{postid}",
    response_model=UpdatePostResponse,
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
    },
    tags="Post"
)
async def update_post(postid: int, userRequest: UpdatePostRequest):
    sessionmaker.query(Post).filter(Post.post_id == postid) \
        .update({Post.title: userRequest.title,
                 Post.content: userRequest.content,
                 Post.post_image_id: userRequest.postImage,
                 Post.update_at: now.date()}, synchronize_session=False)

    sessionmaker.flush()
    sessionmaker.commit()

    return UpdatePostResponse(postId=postid)


@router.delete(
    "/posts/{postid}",
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
    },
    tags="Post"
)
async def delete_post(postid: int):
    sessionmaker.query(Post).filter(Post.post_id == postid).delete()
    sessionmaker.commit()

    return None


@router.get(
    "/posts/recent",
    response_model=List[GetRecentPostResponse],
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
    },
    tags="Post"
)
async def get_recent_post(page: int = 1):
    offset = (page - 1) * 10
    posts = sessionmaker.query(Post).order_by(Post.created_at).offset(offset).limit(10).all()
    result = []

    for post in posts:
        result.append(GetRecentPostResponse(
            postId=post.post_id,
            title=post.title,
            content=post.content,
            postImageId=post.post_image_id,
            created_at=post.created_at)
        )

    return result


@router.get(
    "/posts/most-viewed",
    response_model=List[GetMostViewedPostResponse],
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
    },
    tags="Post"
)
async def get_most_viewed_post():
    posts = sessionmaker.query(Post).outerjoin(User, Post.user_id == User.user_id).order_by(Post.view_cnt).limit(5)
    result = []

    for post in posts:
        result.append(GetMostViewedPostResponse(
            title=post.title,
            content=post.content,
            postImageId=post.post_image_id,
            nickname=post.user.nickname,
            postId=post.post_id
        )
        )

    return result


@router.get(
    "/posts/myself",
    response_model=List[GetMyPostResponse],
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
    },
    tags="Post"
)
async def get_my_post(userId: int, page: int = 1):
    offset = (page - 1) * 10
    posts = sessionmaker.query(Post).filter(Post.user_id == userId) \
        .order_by(Post.view_cnt).offset(offset).limit(10).all()
    result = []

    for post in posts:
        result.append(GetMyPostResponse(
            title=post.title,
            content=post.content,
            postImageId=post.post_image_id,
            postId=post.post_id
        ))

    return result


@router.get(
    "/posts/{postid}",
    response_model=GetPostDetailResponse,
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
    },
    tags="Post"
)
async def get_post_detail(userid: int, postid: int):
    post = sessionmaker.query(Post).join(User, User.user_id == Post.user_id) \
        .filter(Post.post_id == postid) \
        .first()

    sessionmaker.query(Post).filter(Post.post_id == postid) \
        .update({Post.view_cnt: post.view_cnt + 1}, synchronize_session=False)
    sessionmaker.flush()
    sessionmaker.commit()

    if (sessionmaker.query(Scrap) \
            .filter(Scrap.post_id == postid).filter(Scrap.user_id == userid).count() == 0):
        isScrap = False
    else:
        isScrap = True

    return GetPostDetailResponse(
        nickname=post.user.nickname,
        title=post.title,
        content=post.content,
        postImageId=post.post_image_id,
        type=post.type,
        view_cnt=post.view_cnt + 1,
        update_at=post.update_at,
        isScrap=isScrap,
        postid=post.post_id
    )
