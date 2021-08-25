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
                question, answer = NLP_game.NLP.make_qa(row[0])
                return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':question, 'answer':answer})
        else:
                return 'error'

def game_db(app):
        row = app.database.execute(text("""
                                SELECT text FROM diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if row != None:
                sentences, _ = NLP_game.NLP.make_important_senteces(row[0])
                game_text = {'game_text':sentences[1]}
                row = app.database.execute(text("""
                                SELECT game_text FROM game 
                                WHERE game_text = :game_text
                                """),game_text).fetchone()
                if row != None:
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'h', 'answer':row[0]})
                else:
                        row = app.database.execute(text("""
                                SELECT game_text FROM game 
                                """)).fetchall()
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'h', 'answer':99, 'game_text':sentences[1]})
        else:
                return 'error'

def plus_word(app):
        app.database.execute(text("""
                        INSERT INTO game (game_text)
                        VALUES (:game_text)
                        """),request.json).lastrowid
        return request.json