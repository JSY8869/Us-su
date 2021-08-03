from sqlalchemy import text
from flask import jsonify

def R_diary(app):
    userdiary = {"member_id":12, "created_at":'hi'}
    print(userdiary)
    row = app.database.execute(text("""
        SELECT text FROM diary WHERE member_id=member_id and created_at=created_at
    """),userdiary
    ).fetchone()
    return (row[0])