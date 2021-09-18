from flask import request, json
from sqlalchemy import text
from game_src import NLP_game
from server_src.diary import Diary
def gamee(app):
        row = app.database.execute(text("""
                                SELECT text, score_ox FROM diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if Diary.diary_check:
                question, answer = NLP_game.NLP.make_qa(row[0])
                return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':question, 'answer':answer, 'score':Read_Game_Score(app), 'score_ox':row[1]})   
                   
        else:
                return 'error'

def game_db(app):
        row = app.database.execute(text("""
                                SELECT text FROM diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchall()
        if row != None:
                sentences, _ = NLP_game.NLP.make_important_senteces(row[0][0])
                if sentences == None:
                        return 'error'
                game_text = {}
                game_text['game_text'] = sentences
                SQL = 'SELECT game_text FROM game WHERE game_text in ("'
                SQL += '","'.join(game_text['game_text'])
                SQL += '")'
                row = app.database.execute(SQL).fetchall()
                row_string = ""
                for i in range(len(row)):
                        row_string += ''.join(row[i])+' '
                print(row_string)
                if row_string != "":
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'h', 'answer':row_string, 'score':Read_Game_Score(app)} )
                else:
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'h', 'answer':99, 'game_text':sentences[0], 'score':Read_Game_Score(app)})
        else:
                return 'error'

def plus_word(app):
        try:
                game_text = {}
                game_text['game_text'] = request.json['game_text'].split()
                SQL = 'INSERT INTO game (game_text) VALUES ("'
                SQL += '"),("'.join(game_text['game_text'])
                SQL += '")'
                app.database.execute(SQL).lastrowid
                return json.dumps({'game_text':'99'})
        except:
                return 'error'
def Update_Game_Score(app):
        app.database.execute(text("""
                        Update member
                        SET score= :score
                        Where member_id = :member_id
                        """),request.json).lastrowid
        app.database.execute(text("""
                        Update diary
                        SET score_ox= 1
                        Where member_id = :member_id and created_at = :created_at
                        """),request.json).lastrowid
        return (json.dumps({'score':request.json['score']}))

def Read_Game_Score(app):
        row = app.database.execute(text("""
                        select score from member 
                        where member_id = :member_id
                        """),request.json).fetchone()
        return row[0]