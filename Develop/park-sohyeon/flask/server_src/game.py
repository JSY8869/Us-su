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

def game_db(app):
        row = app.database.execute(text("""
                                SELECT text FROM diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if Diary.diary_check:
                sentences = NLP_game.NLP.sentence_extraction(row[0])
                row = app.database.execute(text("""
                                SELECT game_text FROM game 
                                WHERE game_text = :sentences
                                """),sentences).fetchone()
                if row[0] == None:
                        app.database.execute(text("""
                                INSERT INTO game (game_text)
                                VALUES (:game_text)
                                """),sentences).fetchone()
                else:
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'h', 'answer':row[0]})
        else:
                return 'error'