# import flask
# import pymysql
# from flask import Flask, request, render_template, jsonify, json, redirect, url_for, send_from_directory
# import numpy as np
# from keras.models import load_model, save_model
# import matplotlib.pyplot as plt
# import numpy
# import pandas as pd
# from flask_restful import Resource, Api, reqparse, abort
# from flask_sqlalchemy import sqlalchemy
# from sqlalchemy import create_engine, text







# class RegistUser(Resource):
#     def post(self):
#         return {'result': 'ok'}
 
# api.add_resource(RegistUser, '/user')


# @app.route('/') 
# def index():
#     return "모델 불러오기 완료"

# @app.route('/predict', methods=['GET', 'POST'])
# def upload_file():
#     if request.method == 'POST':
#         print(flask.request.files)

# conn = pymysql.connect(host='database-1.cpjkudy7ec1y.ap-northeast-2.rds.amazonaws.com',
#                         user='admin',
#                         password='0cN1KvzzVeQuO7xc7fmt',
#                         db='database-1',
#                         charset='utf8')
# curs = conn.cursor()

# if conn.open:
#     print('connected')

# @app.route('/test', methods = ['POST'])
# def postJsonHandler():
#     print (request.is_json)
#     content = request.get_json()
#     print (content)
#     id = content['id']
#     print ("#####id:",id)
#     sql = "select * from users"
#     curs.execute(sql)
#     rows = curs.fetchall()
#     print(rows)
#     data = {'id':'sohyeon'}
#     return json.dumps(data)

# app.run(host='0.0.0.0', port= 3306)

# @app.route('/predict', methods = ['POST'])  
# def predict():
# #     data = {"success": False}

# #     return flask.jsonify(data)
#     if request.method == 'POST':
#         return render_template('hello.html')

    

# @app.route('/environments/<language>')  
# def environments(language):  
#      return jsonify({"language":language}) #받아온 데이터 다시 전송 









#     model = load_model('model.h5')
#     #model.summary()

