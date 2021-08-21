from flask import Flask, request
from sqlalchemy import create_engine
from diary import Diary
from member import Member
from flask_wtf.csrf import CSRFProtect
from game import gamee

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

@app.route('/diary/Read', methods = ['POST','GET'])
def diary_R():
    return Diary.read(create_app())

@app.route('/diary/Create', methods = ['POST','GET'])
def diary_C():
    return Diary.diary_check(create_app())
    

@app.route('/diary/Update', methods = ['GET','POST'])
def diary_U():
    return Diary.diary_check(create_app())

@app.route('/diary/Delete', methods = ['POST'])
def diary_D():
    return Diary.delete(create_app())

@app.route('/login/Create', methods = ['POST','GET'])
def member_create():
    return Member.register(create_app())

@app.route('/login/Read', methods=['POST','GET'])
def login():
    return Member.login_proc(create_app())
    
@app.route('/diary/Game', methods=['POST','GET'])
def game_q():
    return gamee(create_app())


if __name__== '__main__': # 모델로드
    app.secret_key = 'super secret key'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(host='192.168.0.104', port='8080', debug = True)