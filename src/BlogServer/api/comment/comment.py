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
    }
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


