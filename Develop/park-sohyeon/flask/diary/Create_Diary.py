from sqlalchemy import text
def C_diary(app):
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