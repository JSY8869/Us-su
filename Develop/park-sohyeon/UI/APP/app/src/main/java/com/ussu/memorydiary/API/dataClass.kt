package com.ussu.memorydiary.API

//일기 저장, 수정, 삭제
data class textInfo(
    var member_id: String,
    var created_at: String,
    var text: String,
    var score_ox: Int
)

//회원가입, 로그인
data class memberInfo(
    var member_id: String,
    var member_password: String,
    var score: Int,
    var created_at: String
)

//질문, 답
data class questionInfo(
    var member_id: String,
    var created_at: String,
    var question: String,
    var answer: String,
    var game_text: String,
    var score: Int,
    var score_ox1: Int,
    var score_ox2: Int
)

data class gameText(
    var game_text: String
)
