package com.ussu.memorydiary

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.gson.GsonBuilder
import com.ussu.memorydiary.API.diaryAPI
import com.ussu.memorydiary.API.memberAPI
import com.ussu.memorydiary.API.memberInfo
import com.ussu.memorydiary.API.questionInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameActivity : BaseActivity() {
    private lateinit var AnswerEditText: EditText
    private lateinit var questionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        AnswerEditText = findViewById(R.id.editTextTextAnswer)
        questionTextView = findViewById(R.id.textViewQuestion)

        //서버에서 질문, 답 가져오기
        val BASE_URL = "http://192.168.0.104:8080"
        val id = intent.getStringExtra("id")
        val date = intent.getStringExtra("date")

        var gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(diaryAPI::class.java)
        val callGetQuestionInfo = api.getAnswer(questionInfo("$id", "$date", "0", "0", "0", 0, 100, 100))

        callGetQuestionInfo.enqueue(object : Callback<questionInfo> {
            override fun onResponse(call: Call<questionInfo>, response: Response<questionInfo>) {
                Log.d(ContentValues.TAG, "성공: ${response.raw()}")
                if (response.body() != null) {
                    var question = response.body()!!.question
                    var getAnswer = response.body()!!.answer
                    var score = response.body()!!.score
                    var score_ox = response.body()!!.score_ox1
                    var btnAnswer = findViewById<Button>(R.id.btnCheckAnswer)

                    if (score_ox == 1) {
                        questionTextView.text = "$question"
                        btnAnswer.isVisible = false
                        Toast.makeText(this@GameActivity, "다른 날짜를 선택해주세요.", Toast.LENGTH_LONG).show()
                    } else {
                        //질문 textView에 띄우기
                        questionTextView.text = "$question"


                        btnAnswer.setOnClickListener {
                            //답 입력받기
                            var answer = AnswerEditText.text.toString()
                            Toast.makeText(this@GameActivity, "$answer, $getAnswer", Toast.LENGTH_LONG).show()

                            //답 비교
                            if (answer == getAnswer) { //정답
                                Toast.makeText(this@GameActivity, "정답입니다!", Toast.LENGTH_LONG).show()
                                score = score + 1
                                val BASE_URL = "http://192.168.0.104:8080"

                                var gson = GsonBuilder()
                                    .setLenient()
                                    .create()

                                val retrofit = Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .build()

                                val api = retrofit.create(memberAPI::class.java)
                                val callSaveScore = api.saveScore(memberInfo("$id", "0", score, "$date"))

                                callSaveScore.enqueue(object : Callback<memberInfo> {
                                    override fun onResponse(call: Call<memberInfo>, response: Response<memberInfo>) {
                                        btnAnswer.isVisible = false
                                        var intent = Intent(this@GameActivity, ResultActivity::class.java)
                                        intent.putExtra("id", id)
                                        intent.putExtra("score", score)
                                        startActivity(intent)
                                    }
                                    override fun onFailure(call: Call<memberInfo>, t: Throwable) {
                                        Log.d(ContentValues.TAG, "실패: $t")
                                    }
                                })
                            } else {
                                Toast.makeText(this@GameActivity, "오답입니다! 다시 생각해보세요", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                else {
                    AnswerEditText.setText("작성한 일기가 없습니다.")
                }

            }

            override fun onFailure(call: Call<questionInfo>, t: Throwable) {
                Log.d(ContentValues.TAG, "실패: $t")
            }
        })
    }
}