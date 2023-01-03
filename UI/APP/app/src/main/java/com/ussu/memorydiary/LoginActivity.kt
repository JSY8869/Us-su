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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : BaseActivity() {
    private lateinit var idEditText: EditText //나중에 값을 넣어주겠다
    private lateinit var pwEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        idEditText = findViewById(R.id.editTextTextId)
        pwEditText = findViewById(R.id.editTextTextPassword)
    }

    fun clickLogin(view: View) {

        //입력받은 loginId, loginPw
        var identifier = idEditText.text.toString()
        var password = pwEditText.text.toString()
        val BASE_URL = "http://10.0.2.2:8080"

        var gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(memberAPI::class.java)
        val callReadUser = api.login(User("$identifier", "$password"))

        callReadUser.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    var intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    intent.putExtra("id", identifier)
                    startActivity(intent)
                } else {
                    Toast.makeText(view.context, "id를 다시 입력해주세요.", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d(ContentValues.TAG, "실패: $t")
            }
        })
    }

    fun goSignup(view: View) {
        var intent = Intent(this@LoginActivity, SignupActivity::class.java)
        startActivity(intent)
    }
}