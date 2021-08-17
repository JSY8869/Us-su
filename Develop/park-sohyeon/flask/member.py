from flask import request, session, redirect, url_for
from sqlalchemy import text

class Member():

    def login_proc(app):
        member_id = request.form['member_id']
        member_password = request.form['member_password']
        print(member_id)
        rows = app.database.execute(text("""
            select member_pk, member_id, member_password from member where member_id = member_id 
        """),member_id).fetchall()

        for rs in rows:
            if member_id == rs[1] and member_password == rs[2]:
                session['logFlag'] = True
                session['idx'] = rs[0]
                session['member_id'] = member_id
                return redirect(url_for('main'))
            else:
                return redirect(url_for('login_form'))
 
    def logout():
        session.clear()
        return redirect(url_for('main'))

    def register(app):
        user = request.json
        member_id_cor = app.database.execute(text("""
                        select member_id from member 
                        where member_id = :member_id
                        """), user).fetchone()
        print(member_id_cor)
        if (member_id_cor != None):
            return "error"
        else:
            app.database.execute(text("""
                                INSERT INTO member (
                                    member_id,
                                    member_password
                                )""" + """VALUES (
                                    :member_id,
                                    :member_password
                                )
                                """), user).lastrowid
            return user