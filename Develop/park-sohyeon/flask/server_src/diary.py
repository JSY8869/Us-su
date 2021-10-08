from sqlalchemy import text
from flask import request, json

class Diary():
    
    def diary_check(app): # 일기 작성 유무 확인용
        row = app.database.execute(text("""
                                SELECT text FROM sys.diary WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if row == None: return (False)
        else: return(True)

    def create(app): # 일기 작성 및 수정
        app.database.execute(text("""
                            INSERT INTO sys.diary (created_at, member_id, text)
                            VALUES (:created_at, :member_id, :text)
                            """),request.json).lastrowid
        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':request.json['text']})
    
    def delete(app): # 일기 삭제
        app.database.execute(text("""
                            Delete FROM sys.diary 
                            WHERE member_id= :member_id and created_at= :created_at
                            """),request.json).lastrowid
        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':''})
    
    def read(app): # 일기 불러오기
        row = app.database.execute(text("""
                                SELECT text FROM sys.diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if row != None:
            return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':row[0]})
        else:
            return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':'-1'})