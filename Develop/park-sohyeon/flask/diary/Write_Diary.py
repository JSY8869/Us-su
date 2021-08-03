import Main.server
@app.route('/diary/Write', methods = ['POST'])
def diary():
    app = create_app()
    userdiary = request.json
    print((userdiary))
    new_diary = app.database.execute(text("""
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
    return 200, "success"