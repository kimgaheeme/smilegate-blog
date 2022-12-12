from sqlalchemy import *
from sqlalchemy.orm import sessionmaker
from databases import Database

#
app = {
    'name': 'mysql+pymysql',
    'user': 'root',
    'password': 'dudwls0047',
    'host': 'localhost',
    'dbconn': 'blog',
    'port': '3306'
}

conn_string = f'{app["name"]}://{app["user"]}:{app["password"]}@{app["host"]}:{app["port"]}/{app["dbconn"]}'

class engineconn:

    def __init__(self):
        self.engine = create_engine(conn_string, pool_recycle=500)

    def sessionmaker(self):
        Session = sessionmaker(bind=self.engine)
        session = Session()
        return session

    def connection(self):
        conn = self.engine.connect()
        return conn


metadata = MetaData()
engine = engineconn().engine
database = Database(conn_string)




