package com.ussu.memorydiary.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface memberAPI {
    //회원가입
    @POST("/login/Create")
    fun saveMemberInfo(
        @Body dataClass: memberInfo
    ): Call<memberInfo>

    //로그인 시 id, pw 가져오기
    @POST("/login/Read")
    fun readMemberInfo(
        @Body dataClass: memberInfo
    ): Call<memberInfo>

    @POST("/game/score")
    fun saveScore(
        @Body dataClass: memberInfo
    ): Call<memberInfo>

    @POST("/game/score2")
    fun saveScore2(
        @Body dataClass: memberInfo
    ): Call<memberInfo>
}