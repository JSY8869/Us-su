package com.example.postapi

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BASE_URL = "http://192.168.0.104:8080"

        var gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()



        val api = retrofit.create(API::class.java)
        val callPostText = api.postText(dataClass("2","hi","hi"))
        callPostText.enqueue(object: Callback<dataClass>{
            override fun onResponse(call: Call<dataClass>, response: Response<dataClass>) {
                Log.d(TAG, "성공: ${response.raw()}")
            }

            override fun onFailure(call: Call<dataClass>, t: Throwable) {
                Log.d(TAG, "실패: $t")
            }
        })
    }
}