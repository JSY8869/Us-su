from flask import request, json
from sqlalchemy import text

class Member():

    def login_proc(app): # 로그인 (입력받은 아이디로 DB의 아이디 비밀번호 불러옴)
        row = app.database.execute(text("""
            select member_id, member_password from sys.member where member_id = :member_id 
        """),request.json).fetchone()
        return json.dumps({'member_id':row[0], 'member_password':row[1]})

    def register(app): # 회원 가입
        member_id_cor = app.database.execute(text("""
                        select member_id from sys.member 
                        where member_id = :member_id
                        """), request.json).fetchone() 
        if (member_id_cor != None): return json.dumps({'member_id':'a', 'member_password':'a', 'score':-1}) # 중복 예외처리
        else:
            app.database.execute(text("""
                                INSERT INTO sys.member (member_id, member_password, score)
                                VALUES (:member_id, :member_password, :score)
                                """), request.json).lastrowid
            return request.json