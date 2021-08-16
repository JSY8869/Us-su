from flask import request, session, render_template, redirect, url_for
from flask.helpers import flash
from jinja2.utils import generate_lorem_ipsum
from sqlalchemy import text
from werkzeug.exceptions import RequestEntityTooLarge



class Member():

    def login_proc(app):
        if request.method == 'POST':
            member_id = request.form['id']
            member_password = request.form['password']
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
        else:
            return '값을 잘못 입력했습니다'
 
    def logout():
        session.clear()
        return redirect(url_for('main'))

    def register(app):
        user = request.json
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

    def sign_up(app):
        user = request.json
        if user.method == 'POST' and user.validate_on_submit():
            member_id = user.query.filter_by(member_id = user.member_id.data).first()
            
            if not member_id:
                member_id = User(member_id = user.member_id.data)
                db.session.add(user)
                db.session.commit()
                return user
            else:
                flash("이미 존재하는 아이디 입니다.")
        return user
