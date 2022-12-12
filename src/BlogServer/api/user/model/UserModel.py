from sqlalchemy import Column, TEXT, INT, DATETIME
from sqlalchemy.orm import declarative_base

Base = declarative_base()


class User(Base):
    __tablename__ = 'user'
    user_id = Column(INT, nullable=False, autoincrement=True, primary_key=True)
    email = Column(TEXT, nullable=False)
    nickname = Column(TEXT, nullable=False)
    created_at = Column(DATETIME, nullable=False)