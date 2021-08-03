@app.route('/diary/Road', methods = ['POST','GET'])
def diary():
    app = create_app()
    userdiary = request.json
                                    
    row = app.database.execute(text("""
        SELECT
            text
        FROM test.diary
        WHERE member_id = ?, created_at = ?
    """)
    ).fetchall()
    return jsonify(row)