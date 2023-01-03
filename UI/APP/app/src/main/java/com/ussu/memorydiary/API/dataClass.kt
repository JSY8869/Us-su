package com.ussu.memorydiary.API

//일기 저장, 수정, 삭제
data class Diary(
    var user_id: String,
    var created_at: String,
    var text: String
)

//회원가입, 로그인
data class User(
    var identifier: String,
    var password: String,
)

//질문, 답
data class questionInfo(
    var identifier: String,
    var created_at: String,
    var question: String,
    var answer: String,
    var game_text: String,
    var score: Int,
    var isPlayed1: Int,
    var isPlayed2: Int
)

data class gameText(
    var game_text: String
)
