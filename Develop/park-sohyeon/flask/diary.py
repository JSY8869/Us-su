from sqlalchemy import text
from flask import request

class Diary():
    
    def __init__(self):
        return

    def create(self, app):
        userdiary = request.json
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
        return "success"

    def delete(self, app):
        userdiary = request.json
        app.database.execute(text("""
            Delete FROM diary WHERE member_id=member_id and created_at=created_at
        """),userdiary
        ).lastrowid
        return "success"
    
    def read(self, app):
        userdiary = request.json
        print(userdiary)
        row = app.database.execute(text("""
            SELECT text FROM diary WHERE member_id=member_id and created_at=created_at
        """),userdiary
        ).fetchone()
        return (row[0])

    def update(self, app):
        userdiary = request.json
        app.database.execute(text("""
                        Update diary
                        SET text='213123'
                        Where member_id=member_id and created_at=created_at
                        """),userdiary).lastrowid
        return "success"