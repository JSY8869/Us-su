from sqlalchemy import text
def U_diary(app):
    userdiary = {"member_id":12, "created_at":'hi',"text":'hh'}
    app.database.execute(text("""
                    Update diary
                    SET text='213123'
                    Where member_id=member_id and created_at=created_at
                    """),userdiary).lastrowid
    return "success"