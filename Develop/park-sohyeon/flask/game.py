from flask import request, json
from sqlalchemy import text

def gamee(app):
        user = request.json
        row = app.database.execute(text("""
                        select member_id from member 
                        where member_id = :member_id
                        """), user).fetchone
        return json.dumps({'member_id':user['member_id'], 'created_at':user['created_at'], 'question':1, 'answer':1})