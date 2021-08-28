from flask import request, json
from sqlalchemy import text

class Member():

    def login_proc(app):
        row = app.database.execute(text("""
            select member_id, member_password from member where member_id = :member_id 
        """),request.json).fetchone()
        if request.json['member_id'] == row[0]:
            return json.dumps({'member_id':row[0], 'member_password':row[1]})
        else: return 'error'

    def register(app):
        member_id_cor = app.database.execute(text("""
                        select member_id from member 
                        where member_id = :member_id
                        """), request.json).fetchone()
        if (member_id_cor != None): return "error"
        else:
            app.database.execute(text("""
                                INSERT INTO member (member_id, member_password)
                                VALUES (:member_id, :member_password)
                                """), request.json).lastrowid
            return request.json