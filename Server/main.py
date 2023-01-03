from flask import Flask, request
from game_src import NLP_game, NLP_game2

app = Flask(__name__)
game1 = NLP_game.NLP1()
game2 = NLP_game2.NLP2()

# -------------------------Game--------------------------------
@app.route('/diary/Game/<sentence>', methods=['POST'])
def game_q1(sentence):
    return game1.make_qa(sentence), {'Content-Type': 'application/json'}


@app.route('/diary/Game2/<sentence>', methods=['POST'])
def game_q2(sentence):
    return game2.make_important_word(sentence), {'Content-Type': 'application/json'}


if __name__ == '__main__':  # 모듈로드
    app.secret_key = 'super secret key'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(host='0.0.0.0', port='8081', debug=False)
