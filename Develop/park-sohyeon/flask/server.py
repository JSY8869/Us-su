from flask import Flask, request
from sqlalchemy import create_engine, text
from diary import Diary
from member import User

app = Flask(__name__)
username="root"
password = "0cN1KvzzVeQuO7xc7fmt"
host = "localhost"
db_name = "test"
db_path = "mysql+pymysql://{}:{}@{}:3306/{}?charset=utf8".format(username, password, host, db_name)
def create_app(test_config = None):
        
    app.config.from_pyfile('dbconfig.py')
    database = create_engine(db_path, encoding = 'utf-8')
    app.database = database

    return app

@app.route('/', methods = ['GET'])
def home():
    return "home"

@app.route('/info', methods = ['POST'])
def add():
    create_app()

    user = request.json
    app.database.execute(text("""
                                INSERT INTO member (
                                member_id,
                                member_password,
                                member_name,
                                score
                                ) VALUES (
                                :member_id,
                                :member_password,
                                :score
                                )
                                """), user).lastrowid

    return "success", 200

@app.route('/diary/Read', methods = ['GET'])
def diary_R():
    result = create_app()
    return Diary.read(result)

@app.route('/diary/Create', methods = ['GET'])
def diary_C():
    return Diary.create(create_app())

@app.route('/diary/Update', methods = ['GET'])
def diary_U():
    result = create_app()
    return Diary.update(result)

@app.route('/diary/Delete', methods = ['GET']) # 다 삭제됨
def diary_D():
    return Diary.delete(create_app())

@app.route('/login', methods=['GET', 'POST'])
def login():
    return User.login(create_app())

if __name__== '__main__': # 모델로드
    app.run(host='192.168.0.114', port='8080', debug = True)