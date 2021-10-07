from sqlalchemy import text
from flask import request, json

class Diary():
    
    def diary_check(app): # 일기 작성 유무 확인용
        row = app.database.execute(text("""
                                SELECT text FROM diary WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if row == None: return (False)
        else: return(True)

    def create(app): # 일기 작성 및 수정
        try:
            app.database.execute(text("""
                                INSERT INTO diary (created_at, member_id, text)
                                VALUES (:created_at, :member_id, :text)
                                """),request.json).lastrowid
            return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':request.json['text']})
        except:
            return 'error'
    
    def delete(app): # 일기 삭제
        try:
            app.database.execute(text("""
                                Delete FROM diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).lastrowid
            return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':''})
        except:
            return 'error'
    
    def read(app): # 일기 불러오기
        try:
            row = app.database.execute(text("""
                                    SELECT text FROM diary 
                                    WHERE member_id= :member_id and created_at= :created_at
                                    """),request.json).fetchone()
            if row != None:
                return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':row[0]})
            else:
                return 'error'
        except:
            return 'error'