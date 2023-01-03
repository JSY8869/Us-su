package com.ussu.memorydiary.API

import retrofit2.Call
import retrofit2.http.*

interface diaryAPI {
    //일기 저장
    @POST("/diary/create")
    fun saveDiaryText(
        @Body dataClass: Diary
    ): Call<Diary>

    //일기 가져오기
    @POST("/diary/read")
    fun readDiaryText(
        @Body dataClass: Diary
    ): Call<Diary>

    //일기 삭제
    @POST("/diary/delete")
    fun deleteDiaryText(
        @Body dataClass: Diary
    ): Call<Diary>

    //질문, 정답 가져오기
    @POST("/game1")
    fun getAnswer(
        @Body dataClass: questionInfo
    ): Call<questionInfo>

    @POST("/game2")
    fun getAnswer2(
        @Body dataClass: questionInfo
    ): Call<questionInfo>

    @POST("/game")
    fun getGameText(
        @Body dataClass: gameText
    ): Call<gameText>
}

