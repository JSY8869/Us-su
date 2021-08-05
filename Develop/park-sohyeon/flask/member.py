from flask import Flask, url_for, render_template, request, redirect, session
from flask_sqlalchemy import SQLAlchemy

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80),unique = True)
    password = db.Column(db.String(80))
    
    def __init__(self, username, password):
        self.username = username
        self.password = password
    

    def login():
        if request.method == 'GET':
            return render_template('login.html')
        else:
            name = request.form['username']
            passw = request.form['password']
            try:
                data = User.query.filter_by(username=name, password=passw).first()
                if data is not None:
                    session['logged_in'] = True
                    return redirect(url_for('home'))
                else:
                    return 'no login'
            except:
                return 'no login'
            
    @app.route('/register', methods=['GET', 'POST'])
    def register():
        if request.method == 'POST':
            new_user = User(username=request.form['username'], password=request.form['password'])
            db.session.add(new_user)
            db.session.commit()
            return render_template('login.html')
        return render_template('register.html')
    
    @app.route('/logout')
    def logout():
        session['logged_in'] = False
        return redirect(url_for('home'))
    