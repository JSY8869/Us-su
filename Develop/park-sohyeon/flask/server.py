from sqlite3.dbapi2 import Row
import flask
from flask.globals import session
import pymysql
import sqlite3
from flask import Flask, request, render_template, jsonify, json, redirect, url_for, send_from_directory
from flask_restful import Resource, Api, reqparse, abort
from flask_sqlalchemy import sqlalchemy
from sqlalchemy import create_engine, text
from sqlalchemy.sql.expression import outerjoin, select, table


app = Flask(__name__)

def create_app(test_config = None):
        
    app.config.from_pyfile('dbconfig.py')
    database = create_engine(app.config['DB_URL'], encoding = 'utf-8')
    app.database = database

    return app


# class Flight(db.Model):
#     __tablename__ = "flights"
#     id = db.Column(db.Integer, primary_key=True)
#     origin = db.Column(db.String, nullable=False)
#     destination = db.Column(db.String, nullable=False)    
#     duration = db.Column(db.Integer, nullable=False)    




@app.route('/', methods = ['GET'])
def home():
    return "home"

@app.route('/info', methods = ['POST'])
def add():
    create_app()

    user = request.json
    print(request.jason)
    user_info = app.database.execute(text("""
                                        INSERT INTO member (
                                        member_id,
                                        member_name,
                                        score
                                       ) VALUES (
                                        :member_id,
                                        :member_name,
                                        :score
                                       )
                                        """), user).lastrowid

    return "success", 200

@app.route('/diary', methods = ['POST'])
def diary():
    create_app()
    

    user = request.json

    user_diary = app.database.execute(text("""
                                        INSERT INTO diary (
                                        member_id,
                                        created_at,
                                        text
                                       ) VALUES (
                                        :member_id,
                                        :created_at,
                                        :text
                                       )
                                        """), user).lastrowid
    return "success", 200

# conn = pymysql.connect()
# temp = list()
# for row in conn.execute('SELECT * FROM test.member'):
#         temp.append(row)
# for row in conn.execute('SELECT * FROM test.diary'):
#         temp.append(row)
# df = pd.DataFrame(temp)


# session.query(member).\
# outerjoin(diary, member.member_id == diary.member_id).__module__
# all()

@app.errorhandler(500)
def pageNotFound(error):
    return "Server Error"

if __name__== '__main__': # 모델로드
    app.run(host='127.0.0.1', port='5000', debug = True)