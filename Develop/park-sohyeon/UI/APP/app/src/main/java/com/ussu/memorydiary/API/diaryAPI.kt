package com.ussu.memorydiary.API

import retrofit2.Call
import retrofit2.http.*

interface diaryAPI {
    //일기 저장
    @POST("/diary/Create")
    fun saveDiaryText(
        @Body dataClass: textInfo
    ): Call<textInfo>

    //일기 가져오기
    @POST("/diary/Read")
    fun readDiaryText(
        @Body dataClass: textInfo
    ): Call<textInfo>

    //일기 삭제
    @POST("/diary/Delete")
    fun deleteDiaryText(
        @Body dataClass: textInfo
    ): Call<textInfo>

    //질문, 정답 가져오기
    @POST("/diary/Game")
    fun getAnswer(
        @Body dataClass: questionInfo
    ): Call<questionInfo>

    @POST("/diary/Game2")
    fun getAnswer2(
        @Body dataClass: questionInfo
    ): Call<questionInfo>

    @POST("/game")
    fun getGameText(
        @Body dataClass: gameText
    ): Call<gameText>
}

