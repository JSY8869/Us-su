from sqlalchemy import text

class Diary():
    
    def __init__(self):
        return

    def C_diary(self, app):
        userdiary = {"member_id":12, "created_at":'hi',"text":'hh'}
        app.database.execute(text("""
                        INSERT INTO diary (
                            created_at,
                            member_id,
                            text
                        )""" + """VALUES (
                            :created_at,
                            :member_id,
                            :text
                        )
                        """),userdiary).lastrowid
        return "success"

    def D_diary(self, app):
        userdiary = {"member_id":12, "created_at":'hi'}
        app.database.execute(text("""
            Delete FROM diary WHERE member_id=member_id and created_at=created_at
        """),userdiary
        ).lastrowid
        return "success"
    
    def R_diary(self, app):
        userdiary = {"member_id":12, "created_at":'hi'}
        print(userdiary)
        row = app.database.execute(text("""
            SELECT text FROM diary WHERE member_id=member_id and created_at=created_at
        """),userdiary
        ).fetchone()
        return (row[0])

    def U_diary(self, app):
        userdiary = {"member_id":12, "created_at":'hi',"text":'hh'}
        app.database.execute(text("""
                        Update diary
                        SET text='213123'
                        Where member_id=member_id and created_at=created_at
                        """),userdiary).lastrowid
        return "success"