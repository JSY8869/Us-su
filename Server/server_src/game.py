from flask import request, json
from sqlalchemy import text
from game_src import NLP_game
from game_src import NLP_game2
def game_1(app): # 게임 1 실행 코드
        row = []
        row = app.database.execute(text("""
                                SELECT text, score_ox1 FROM sys.diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchone()
        if row != None:
                question, answer = NLP_game.NLP1.make_qa(row[0])
                score = Read_Game_Score(app)
                if row[1] != 1:
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':question, 'answer':answer, 'score':score, 'score_ox1':0})   
                else:
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':question, 'answer':answer, 'score':score, 'score_ox1':1})
        else:
                return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'', 'answer':'', 'score':0, 'score_ox1':2})
def game_2(app): # 게임 2 실행 코드
        row = []
        # 일기 불러오기
        row = app.database.execute(text("""
                                SELECT text, score_ox2 FROM sys.diary 
                                WHERE member_id= :member_id and created_at= :created_at
                                """),request.json).fetchall()

        if row != []:
                if row[0][1] == 1: # 게임을 이미 진행한 경우
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'', 'answer':'', 'score':0, 'score_ox2':1})
                
                important_words = NLP_game2.NLP2.make_important_word(row[0][0]) # 명사 추출
                
                if important_words == None: # 명사 추출에 실패한 경우
                        json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'', 'answer':'', 'score':0, 'score_ox2':2} )
                
                game_text = {}
                game_text['game_text'] = important_words
                SQL = 'SELECT game_text FROM sys.game WHERE game_text in ("'
                SQL += '","'.join(game_text['game_text'])
                SQL += '")'
                row = []
                row = app.database.execute(SQL).fetchall()
                # 장소 관련 단어가 있는 경우    
                if row != []: 
                        row_string = ""
                        for i in range(len(row)):
                                row_string += ''.join(row[i])+' '
                        print(row_string)
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'h', 'answer':row_string, 'score':Read_Game_Score(app), 'score_ox2':0} )
                # 장소 관련 단어가 없는 경우
                else: 
                        important_word = ""
                        for i in range(len(important_words)-1):
                                important_word += ''.join(important_words[i])+' '
                        important_word += ''.join(important_words[-1])
                        return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'h', 'answer':99, 'game_text':important_word, 'score':Read_Game_Score(app), 'score_ox2':0})
        else:
                return json.dumps({'member_id':request.json['member_id'], 'created_at':request.json['created_at'], 'question':'', 'answer':'', 'score':0, 'score_ox2':3} )

def plus_word(app): # DB에 '장소'관련 단어 추가
        game_text = {}
        game_text['game_text'] = request.json['game_text'].split()
        SQL = 'INSERT INTO sys.game (game_text) VALUES ("'
        SQL += '"),("'.join(game_text['game_text'])
        SQL += '")'
        app.database.execute(SQL).lastrowid
        return json.dumps({'game_text':'99'})
                
def Update_Game_Score(app): # 게임 1 완료 정보 저장
        app.database.execute(text("""
                        Update sys.member
                        SET score = :score
                        Where member_id = :member_id
                        """),request.json).lastrowid
        app.database.execute(text("""
                        Update sys.diary
                        SET score_ox1 = 1
                        Where member_id = :member_id and created_at = :created_at
                        """),request.json).lastrowid
        return (json.dumps({'score':request.json['score']}))

def Update_Game_Score2(app): # 게임 2 완료 정보 저장
        app.database.execute(text("""
                        Update sys.member
                        SET score = :score
                        Where member_id = :member_id
                        """),request.json).lastrowid
        app.database.execute(text("""
                        Update sys.diary
                        SET score_ox2 = 1
                        Where member_id = :member_id and created_at = :created_at
                        """),request.json).lastrowid
        return (json.dumps({'score':request.json['score']}))

def Read_Game_Score(app): # 사용자의 score 현황 불러오기
        row = app.database.execute(text("""
                        select score from sys.member 
                        where member_id = :member_id
                        """),request.json).fetchone()
        return row[0]