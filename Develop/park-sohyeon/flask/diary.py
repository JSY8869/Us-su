from sqlalchemy import text
from flask import request, json

class Diary():
    
    def diary_check(app):
        userdiary = request.json
        row = app.database.execute(text("""
            SELECT text FROM diary WHERE member_id= :member_id and created_at= :created_at
        """),userdiary
        ).fetchone()
        if row == None: return (Diary.create(app))
        else: return(Diary.update(app))

    def create(app):
        userdiary = request.json
        print(request.json)
        app.database.execute(text("""
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
        return json.dumps({'member_id':userdiary['member_id'], 'created_at':userdiary['created_at'], 'text':userdiary['text']})

    
    def delete(app):
        userdiary = request.json
        app.database.execute(text("""
            Delete FROM diary WHERE member_id= :member_id and created_at= :created_at
        """),userdiary
        ).lastrowid
        return json.dumps({'member_id':userdiary['member_id'], 'created_at':userdiary['created_at'], 'text':''})
    
    def read(app):
        userdiary = request.json
        row = app.database.execute(text("""
            SELECT text FROM diary WHERE member_id= :member_id and created_at= :created_at
        """),userdiary
        ).fetchone()
        return json.dumps({'member_id':userdiary['member_id'], 'created_at':userdiary['created_at'], 'text':row[0]})

    def update(app):
        userdiary = request.json
        app.database.execute(text("""
                        Update diary
                        SET text= :text
                        Where member_id = :member_id and created_at = :created_at
                        """),userdiary).lastrowid
        return json.dumps({'member_id':userdiary['member_id'], 'created_at':userdiary['created_at'], 'text':userdiary['text']})