from fastapi import FastAPI
from api.user import user
from api.post import post
from api.scrap import scrap
from api.comment import comment

app = FastAPI()


def include_router(app):
    app.include_router(user.router)
    app.include_router(post.router)
    app.include_router(scrap.router)
    app.include_router(comment.router)


def start_application():
    app = FastAPI()
    include_router(app)
    return app


app = start_application()