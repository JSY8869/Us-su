from flask import Flask, request
from sqlalchemy import create_engine, text
from diary import Diary
import pymysql

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

# class User(db.Moedl):
#     """Create user table"""
#     id = app.Column(app.Integer, primary_key=True)
#     username = db.Column(db.String(80), unique = True)
#     password = db.Column(db.String(80))
#     def __init__(self, username, password):
#         self.username = username
#         self.password = password



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
    result = create_app()
    return Diary.create(result)

@app.route('/diary/Update', methods = ['GET'])
def diary_U():
    result = create_app()
    return Diary.update(result)

@app.route('/diary/Delete', methods = ['GET']) # 다 삭제됨
def diary_D():
    return Diary.delete(create_app())

import re
from sys import meta_path
from types import MethodDescriptorType
from flask import Flask, request, session, render_template, redirect, url_for
import pymysql

@app.route('/main')
def main():
    return render_template('main.html')

@app.route('/login_form')
def login_form():
    return render_template('login_form.html')

@app.route('/login_proc', methods=['POST'])
def login_proc():
    if request.method == 'POST':
        member_id = request.form['id']
        userPassword = request.form['pwd']
        if len(member_id) == 0 or len(userPassword) == 0:
            return 'not found!!'
        else:
            rows = create_app().database.execute(text("""
                select member_pk, member_id, member_password from member where member_id = member_id
            """),member_id).fetchall()
            print (rows)
            for rs in rows:
                if member_id == rs[1] and userPassword == rs[2]:
                    session['logFlag'] = True
                    session['idx'] = rs[0]
                    session['member_id'] = member_id
                    return redirect(url_for('main'))
                else:
                    return redirect(url_for('login_form'))
    else:
        return '잘 못 박 소 현'

@app.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('main'))

if __name__== '__main__': # 모델로드
    app.secret_key = 'super secret key'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(host='192.168.0.113', port='8080', debug = True)