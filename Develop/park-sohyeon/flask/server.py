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
    user_info = app.database.execute(text("""
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

@app.route('/diary', methods = ['POST','GET'])
def diary():
    create_app()
    created_at="hi"
    user_diary = app.database.execute(text("""
                                        INSERT INTO diary (created_at,member_id,text)
                                        VALUES (created_at,member_id,text)
                                    """), request.json).lastrowid
    row = app.database.execute(text("""			# 1)
        SELECT
            created_at
        FROM test.diary
    """), {
        	'created_at' : created_at
        }).fetchall()
        
    timeline = [{							# 2)
        'created_at' : rows['created_at']
    } for rows in row]
    
    return jsonify({						# 3)
        'created_at' : created_at
    })






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

if __name__== '__main__': # 모델로드
    app.run(host='192.168.0.104', port='8080', debug = True)