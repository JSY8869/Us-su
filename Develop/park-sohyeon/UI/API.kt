package com.example.postapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("/diary")
    fun postText(@Body dataClass: dataClass): Call<dataClass>
}