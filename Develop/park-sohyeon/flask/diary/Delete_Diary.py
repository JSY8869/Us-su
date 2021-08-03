from sqlalchemy import text
from flask import jsonify

def D_diary(app):
    userdiary = {"member_id":12, "created_at":'hi'}
    app.database.execute(text("""
        Delete FROM diary WHERE member_id=member_id and created_at=created_at
    """),userdiary
    ).lastrowid
    return "success"