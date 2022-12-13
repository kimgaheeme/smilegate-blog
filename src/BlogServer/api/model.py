from sqlalchemy import Column, TEXT, INT, DATETIME, ForeignKey, Enum
from sqlalchemy.orm import declarative_base, relationship, backref
from api.post.dto.PostRequest import PostType

Base = declarative_base()


class User(Base):
    __tablename__ = 'user'
    user_id = Column(INT, nullable=False, autoincrement=True, primary_key=True)
    email = Column(TEXT, nullable=False)
    nickname = Column(TEXT, nullable=False)
    created_at = Column(DATETIME, nullable=False)


class Post(Base):
    __tablename__ = 'post'
    post_id = Column(INT, nullable=False, autoincrement=True, primary_key=True)
    user_id = Column(INT, ForeignKey('user.user_id'), nullable=False)
    title = Column(TEXT, nullable=False)
    content = Column(TEXT, nullable=False)
    post_image_id = Column(TEXT, nullable=False, default="")
    type = Column(Enum(PostType), nullable=False)
    view_cnt = Column(INT, nullable=False, default=0)
    update_at = Column(DATETIME, nullable=False)
    created_at = Column(DATETIME, nullable=False)
    user = relationship("User", backref=backref("post", order_by=post_id))


class Scrap(Base):
    __tablename__ = 'post_scrap'
    post_scrap_id = Column(INT, nullable=False, autoincrement=True, primary_key=True)
    user_id = Column(INT, ForeignKey('user.user_id'), nullable=False)
    post_id = Column(INT, ForeignKey('post.post_id'), nullable=False)
    created_at = Column(DATETIME, nullable=False)
    user = relationship("User", backref=backref("post_scrap", order_by=post_scrap_id))
    post = relationship("Post", backref=backref("post_scrap", order_by=post_scrap_id))


class Comment(Base):
    __tablename__ = 'post_comment'
    comment_id = Column(INT, nullable=False, autoincrement=True, primary_key=True)
    user_id = Column(INT, ForeignKey('user.user_id'), nullable=False)
    post_id = Column(INT, ForeignKey('post.post_id'), nullable=False)
    content = Column(TEXT, nullable=False)
    update_at = Column(DATETIME, nullable=False)
    created_at = Column(DATETIME, nullable=False)
    user = relationship("User", backref=backref("post_comment", order_by=comment_id))
    post = relationship("Post", backref=backref("post_comment", order_by=comment_id))