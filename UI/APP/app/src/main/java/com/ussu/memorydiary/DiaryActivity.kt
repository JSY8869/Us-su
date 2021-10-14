package com.ussu.memorydiary

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.gson.GsonBuilder
import com.ussu.memorydiary.API.diaryAPI
import com.ussu.memorydiary.API.textInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class DiaryActivity : BaseActivity() {

    private lateinit var dateTextView: TextView
    private lateinit var diaryEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        dateTextView = findViewById(R.id.textViewDate)
        diaryEditText = findViewById(R.id.EditTextDiary)

        diaryEditText.setFilters(arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            val ps: Pattern =
                Pattern.compile("^[가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55\\u2B93 .?!]*$")
            if (source == "" || ps.matcher(source).matches()) {
                return@InputFilter source
            } else {
                Toast.makeText(this@DiaryActivity, "한글만 입력 가능합니다.", Toast.LENGTH_LONG).show()
                ""
            }
        }))

        val date = intent.getStringExtra("date")
        dateTextView.text = "$date"

        val BASE_URL = "http://3.35.88.89:8080"
        val id = intent.getStringExtra("id")

        var gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(diaryAPI::class.java)
        val callReadDiaryText = api.readDiaryText(textInfo("$id", "$date", "0", 0))

        callReadDiaryText.enqueue(object : Callback<textInfo> {
            override fun onResponse(call: Call<textInfo>, response: Response<textInfo>) {
                if (response.body()!!.text != "-1") {
                    var diaryText = response.body()!!.text
                    diaryEditText.setText("$diaryText")
                    diaryEditText.isEnabled = false

                    var btnDelete = findViewById<Button>(R.id.btnDeleteText)
                    btnDelete.setOnClickListener {
                        val BASE_URL = "http://3.35.88.89:8080"
                        val date = intent.getStringExtra("date")
                        val id = intent.getStringExtra("id")
                        var text = diaryEditText.text.toString()

                        var gson = GsonBuilder()
                            .setLenient()
                            .create()

                        val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()

                        val api = retrofit.create(diaryAPI::class.java)
                        val callDeleteDiaryText = api.deleteDiaryText(textInfo("$id", "$date", "$text", 0))

                        callDeleteDiaryText.enqueue(object : Callback<textInfo> {
                            override fun onResponse(call: Call<textInfo>, response: Response<textInfo>) {
                                diaryEditText.setText("")
                                diaryEditText.isEnabled = true

                                var btnSave = findViewById<Button>(R.id.btnSaveText)
                                btnSave.setOnClickListener {
                                    val BASE_URL = "http://3.35.88.89:8080"
                                    val id = intent.getStringExtra("id")
                                    var text = diaryEditText.text.toString()

                                    var gson = GsonBuilder()
                                        .setLenient()
                                        .create()

                                    val retrofit = Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .build()

                                    val api = retrofit.create(diaryAPI::class.java)
                                    val callSaveDiaryText = api.saveDiaryText(textInfo("$id", "$date", "$text", 0))

                                    callSaveDiaryText.enqueue(object : Callback<textInfo> {
                                        override fun onResponse(call: Call<textInfo>, response: Response<textInfo>) {
                                            diaryEditText.setText("$text")
                                            diaryEditText.isEnabled = false

                                            Log.d(TAG, "성공: ${response.raw()}")
                                        }

                                        override fun onFailure(call: Call<textInfo>, t: Throwable) {
                                            Log.d(TAG, "실패: $t")
                                        }
                                    })
                                }
                                Log.d(TAG, "성공: ${response.raw()}")
                            }

                            override fun onFailure(call: Call<textInfo>, t: Throwable) {
                                Log.d(TAG, "실패: $t")
                            }
                        })

                    }

                    var btnEdit = findViewById<Button>(R.id.btnEditText) //수정 누르면
                    btnEdit.setOnClickListener {
                        diaryEditText.isEnabled = true

                        var btnSave = findViewById<Button>(R.id.btnSaveText)
                        btnSave.setOnClickListener {
                            val BASE_URL = "http://3.35.88.89:8080"
                            val id = intent.getStringExtra("id")
                            var text = diaryEditText.text.toString()

                            var gson = GsonBuilder()
                                .setLenient()
                                .create()

                            val retrofit = Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build()

                            val api = retrofit.create(diaryAPI::class.java)
                            val callSaveDiaryText =
                                api.saveDiaryText(textInfo("$id", "$date", "$text", 0))

                            callSaveDiaryText.enqueue(object : Callback<textInfo> {
                                override fun onResponse(call: Call<textInfo>, response: Response<textInfo>) {
                                    diaryEditText.setText("$text")
                                    diaryEditText.isEnabled = false

                                    Log.d(TAG, "성공: ${response.raw()}")
                                }

                                override fun onFailure(call: Call<textInfo>, t: Throwable) {
                                    Log.d(TAG, "실패: $t")
                                }
                            })
                        }


                    }
                    Log.d(TAG, "성공: ${response.raw()}")

                } else {
                    diaryEditText.isEnabled = true

                    var btnSave = findViewById<Button>(R.id.btnSaveText)
                    btnSave.setOnClickListener {
                        val BASE_URL = "http://3.35.88.89:8080"
                        val id = intent.getStringExtra("id")
                        var text = diaryEditText.text.toString()

                        var gson = GsonBuilder()
                            .setLenient()
                            .create()

                        val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()

                        val api = retrofit.create(diaryAPI::class.java)
                        val callSaveDiaryText = api.saveDiaryText(textInfo("$id", "$date", "$text", 0))

                        callSaveDiaryText.enqueue(object : Callback<textInfo> {
                            override fun onResponse(call: Call<textInfo>, response: Response<textInfo>) {
                                diaryEditText.setText("$text")
                                diaryEditText.isEnabled = false



                                Log.d(TAG, "성공: ${response.raw()}")
                            }

                            override fun onFailure(call: Call<textInfo>, t: Throwable) {
                                Log.d(TAG, "실패: $t")
                            }
                        })
                    }
                }
            }

            override fun onFailure(call: Call<textInfo>, t: Throwable) {
                Log.d(TAG, "실패: $t")
            }
        })
    }
}
