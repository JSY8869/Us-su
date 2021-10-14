from flask import Flask
from sqlalchemy import create_engine
from server_src.diary import Diary
from server_src.member import Member
from server_src.game import game_1, game_2, plus_word, Update_Game_Score, Update_Game_Score2

app = Flask(__name__)
username="ussu"
password = "qwer1234"
host = "database-2.cpjkudy7ec1y.ap-northeast-2.rds.amazonaws.com"
db_name = "sys"
db_path = "mysql+pymysql://{}:{}@{}:3306/{}?charset=utf8".format(username, password, host, db_name)
def create_app(test_config = None):
    app.config.from_pyfile('dbconfig.py')
    database = create_engine(db_path, encoding = 'utf-8')
    app.database = database

    return app

# -------------------------Diary--------------------------------
@app.route('/diary/Read', methods = ['POST','GET']) # 일기 불러오기
def diary_R():
    return Diary.read(create_app())

@app.route('/diary/Create', methods = ['POST','GET']) # 일기 수정
def diary_C():
        return Diary.create(create_app())

@app.route('/diary/Delete', methods = ['POST']) # 일기 삭제
def diary_D():
    return Diary.delete(create_app())


# -------------------------Member--------------------------------
@app.route('/login/Create', methods = ['POST','GET'])
def member_create():
    return Member.register(create_app())

@app.route('/login/Read', methods=['POST','GET'])
def login():
    return Member.login_proc(create_app())


# -------------------------Game--------------------------------
@app.route('/diary/Game', methods=['POST','GET'])
def game_q1():
    return game_1(create_app())
    
@app.route('/diary/Game2', methods=['POST','GET'])
def game_q2():
    return game_2(create_app())

@app.route('/game', methods=['POST','GET'])
def game_plus_word():
    return plus_word(create_app())

@app.route('/game/score', methods=['POST','GET'])
def game_score1():
    return Update_Game_Score(create_app())

@app.route('/game/score2', methods=['POST','GET'])
def game_score2():
    return Update_Game_Score2(create_app())

if __name__== '__main__': # 모듈로드
    app.secret_key = 'super secret key'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(host='0.0.0.0', port='8080', debug = False)