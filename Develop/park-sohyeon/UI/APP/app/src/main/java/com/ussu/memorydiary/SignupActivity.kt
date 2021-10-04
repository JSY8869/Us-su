package com.ussu.memorydiary

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.ussu.memorydiary.API.memberAPI
import com.ussu.memorydiary.API.memberInfo
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
        var id = idEditText.text.toString()
        var pw = pwEditText.text.toString()
        var score = 0

        when {
            id.isEmpty() -> { //id를 입력하지 않은 경우
                Toast.makeText(view.context, "id를 입력해주세요", Toast.LENGTH_LONG).show()
            }
            pw.isEmpty() -> { //password를 입력하지 않은 경우
                Toast.makeText(view.context, "password를 입력해주세요", Toast.LENGTH_LONG).show()
            }
            else -> { //회원가입 성공
                //서버로 id, pw 전달
                val BASE_URL = "http://192.168.0.104:8080"

                var gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                val api = retrofit.create(memberAPI::class.java)
                val callSaveMemberInfo = api.saveMemberInfo(memberInfo(id, pw, score, "0"))

                callSaveMemberInfo.enqueue(object : Callback<memberInfo> {
                    override fun onResponse(call: Call<memberInfo>, response: Response<memberInfo>) {
                        var intent = Intent(this@SignupActivity, LoginActivity::class.java)
                        startActivity(intent)
                        Log.d(ContentValues.TAG, "성공: ${response.raw()}")
                    }

                    override fun onFailure(call: Call<memberInfo>, t: Throwable) {
                        Log.d(ContentValues.TAG, "실패: $t")
                    }
                })
            }
        }

    }

}

