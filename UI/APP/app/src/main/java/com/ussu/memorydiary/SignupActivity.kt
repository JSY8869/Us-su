package com.ussu.memorydiary

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.ussu.memorydiary.API.memberAPI
import com.ussu.memorydiary.API.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : BaseActivity() {

    private lateinit var idEditText: EditText //나중에 값을 넣어주겠다
    private lateinit var pwEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        idEditText = findViewById(R.id.editTextTextId)
        pwEditText = findViewById(R.id.editTextTextPassword)
    }

    fun clickSignup(view: View) {
        //회원가입
        var identifier = idEditText.text.toString()
        var password = pwEditText.text.toString()

        when {
            identifier.isEmpty() -> { //id를 입력하지 않은 경우
                Toast.makeText(view.context, "id를 입력해주세요", Toast.LENGTH_LONG).show()
            }
            password.isEmpty() -> { //password를 입력하지 않은 경우
                Toast.makeText(view.context, "password를 입력해주세요", Toast.LENGTH_LONG).show()
            }
            else -> { //회원가입 성공
                //서버로 id, pw 전달
                val BASE_URL = "http://10.0.2.2:8080"

                var gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                val api = retrofit.create(memberAPI::class.java)
                val callSaveUser = api.register(User(identifier, password))

                callSaveUser.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            var intent = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }
                        Log.d(ContentValues.TAG, "성공: ${response.raw()}")
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d(ContentValues.TAG, "실패: $t")
                    }
                })
            }
        }

    }

}

