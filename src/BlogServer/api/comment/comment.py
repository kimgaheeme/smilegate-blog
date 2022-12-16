from typing import List

from fastapi import APIRouter, HTTPException, status

from api.model import Post
from api.model import User
from api.model import Comment
from db_conn import sessionmaker
from _datetime import datetime
from api.comment.dto.CommentRequest import *
from api.comment.dto.CommentResponse import *

router = APIRouter()
now = datetime.now()


@router.post(
    "/posts/{postid}/comments",
    response_model=CreateCommentResponse,
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
    tags="Comment"
)
async def create_comment(userId: int, postid: int, userRequest: CreateCommentRequest):
    add_comment = Comment(
        user_id=userId,
        post_id=postid,
        content=userRequest.content,
        update_at=now.date(),
        created_at=now.date())

    sessionmaker.add(add_comment)
    sessionmaker.flush()
    sessionmaker.commit()

    return CreateCommentResponse(commentId=add_comment.comment_id)


@router.put(
    "/posts/comments/{commentid}",
    response_model=UpdateCommentResponse,
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
    tags="Comment"
)
async def update_comment(commentid: int, userRequest: UpdateCommentRequest):
    sessionmaker.query(Comment).filter(Comment.comment_id == commentid) \
        .update({Post.content: userRequest.content,
                 Post.update_at: now.date()}, synchronize_session=False)

    sessionmaker.flush()
    sessionmaker.commit()

    return UpdateCommentResponse(commentId=commentid)


@router.delete(
    "/posts/comments/{commentid}",
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
    tags="Comment"
)
async def delete_comment(commentid: int):
    if sessionmaker.query(Comment).filter(Comment.comment_id == commentid) \
            .count() == 0:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN)
    else:
        sessionmaker.query(Comment).filter(Comment.comment_id == commentid).delete()
        sessionmaker.commit()

        return None


@router.get(
    "/posts/{postid}/comments",
    response_model=List[GetCommentResponse],
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
    tags="Comment"
)
async def get_comment(postid: int, page: int = 1):
    offset = (page - 1) * 10
    comments = sessionmaker.query(Comment) \
        .join(User, Comment.user_id == User.user_id).order_by(Comment.created_at) \
        .filter(Comment.post_id == postid) \
        .offset(offset).limit(10).all()
    result = []

    for comment in comments:
        result.append(GetCommentResponse(
            nickname=comment.user.nickname,
            commentId=comment.comment_id,
            content=comment.content,
            update_at=comment.update_at
        ))

    return result
