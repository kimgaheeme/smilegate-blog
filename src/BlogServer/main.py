from fastapi import FastAPI
from api.user import user
from api.post import post

app = FastAPI()


def include_router(app):
    app.include_router(user.router)
    app.include_router(post.router)


def start_application():
    app = FastAPI()
    include_router(app)
    return app


app = start_application()