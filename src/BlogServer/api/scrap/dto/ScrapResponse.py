from pydantic import BaseModel


class CreateScrapResponse(BaseModel):
    scrapId: int
