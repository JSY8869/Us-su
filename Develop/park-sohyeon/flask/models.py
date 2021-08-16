# from flask_sqlalchemy import SQLAlchemy

# db = SQLAlchemy() #SQLAlchemy를 사용해 데이터베이스 저장

# class User(db.Model): #데이터 모델을 나타내는 객체 선언
#     __tablename__ = 'user_table' #테이블 이름
    
#     member_pk = db.Column(db.Integer, primary_key=True)
#     member_id = db.Column(db.String(32), unique=True, nullable=False)
#     member_password = db.Column(db.String(8), nullable=False)

#     def __init__(self, member_password):
#         self.set_password(member_password)
    
#     def set_password(self, member_password):
#         self.member_password = generate_password_hash(member_password)
 
#     def check_password(self, member_password):
#         return check_password_hash(self.member_password, member_password)


