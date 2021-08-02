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

@app.route('/diary', methods = ['POST','GET'])
def diary():
    app = create_app()
    userdiary = request.json
    print((userdiary))
    new_diary = app.database.execute(text("""
                        INSERT INTO diary (
                            created_at,
                            member_id,
                            text
                        )""" + """VALUES (
                            :created_at,
                            :member_id,
                            :text
                        )
                        """),userdiary).lastrowid
                                    
    row = app.database.execute(text("""
        SELECT
            created_at
        FROM test.diary
    """)
    ).fetchall()
        
    timeline = [{
        'created_at' : rows['created_at']
    } for rows in row]
    return jsonify(timeline[0])


if __name__== '__main__': # 모델로드
    app.run(host='192.168.0.104', port='8080', debug = True)