from flask import Flask, render_template, request
from sqlalchemy import create_engine
from diary import Diary
from member import Member


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

@app.route('/diary/Read', methods = ['GET'])
def diary_R():
    return Diary.read(create_app())

@app.route('/diary/Create', methods = ['GET'])
def diary_C():
    return Diary.create(create_app())

@app.route('/diary/Update', methods = ['GET'])
def diary_U():
    return Diary.update(create_app())

@app.route('/diary/Delete', methods = ['GET']) # 다 삭제됨
def diary_D():
    return Diary.delete(create_app())

@app.route('/login/Create', methods = ['POST','GET'])
def member_create():
    return Member.register(create_app())

@app.route('/login_proc', methods=['POST'])
def login():
    return Member.login_proc(create_app())
@app.route('/logout')

@app.route('/main')
def main():
    return render_template('main.html')


if __name__== '__main__': # 모델로드
    app.secret_key = 'super secret key'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(host='192.168.0.104', port='8080', debug = True)