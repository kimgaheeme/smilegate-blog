from fastapi import FastAPI
from api.user import user

app = FastAPI()


def include_router(app):
    app.include_router(user.router)


def start_application():
    app = FastAPI()
    include_router(app)
    return app

app = start_application()