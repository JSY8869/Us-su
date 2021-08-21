from flask import request, json
from sqlalchemy import text
from game_src import NLP_game
from server_src.diary import Diary
def gamee(app):
        row = app.database.execute(text("""
                                SELECT text FROM diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if Diary.diary_check:
                question, answer = NLP_game.NLP(row[0])
                return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':question, 'answer':answer})
        else:
                return 'error'