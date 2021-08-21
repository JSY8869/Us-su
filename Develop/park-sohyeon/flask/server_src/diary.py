from sqlalchemy import text
from flask import request, json

class Diary():
    
    def diary_check(app):
        row = app.database.execute(text("""
                                SELECT text FROM diary WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if row == None: return (False)
        else: return(True)

    def create(app):
        app.database.execute(text("""
                            INSERT INTO diary (created_at, member_id, text)
                            VALUES (:created_at, :member_id, :text)
                            """),request.json).lastrowid
        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':request.json['text']})
    
    def delete(app):
        app.database.execute(text("""
                            Delete FROM diary 
                            WHERE member_id= :member_id and created_at= :created_at
                            """),request.json).lastrowid
        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':''})
    
    def read(app):
        row = app.database.execute(text("""
                                SELECT text FROM diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':row[0]})

    def update(app):
        app.database.execute(text("""
                        Update diary
                        SET text= :text
                        Where member_id = :member_id and created_at = :created_at
                        """),request.json).lastrowid
        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'text':request.json['text']})