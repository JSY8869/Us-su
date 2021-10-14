package com.ussu.memorydiary

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.gson.GsonBuilder
import com.ussu.memorydiary.API.diaryAPI
import com.ussu.memorydiary.API.gameText
import com.ussu.memorydiary.API.memberAPI
import com.ussu.memorydiary.API.memberInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationActivity : BaseActivity() {
    private lateinit var CB0: CheckBox
    private lateinit var CB1: CheckBox
    private lateinit var CB2: CheckBox
    private lateinit var CB3: CheckBox
    private lateinit var CB4: CheckBox
    private lateinit var CB5: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val date = intent.getStringExtra("date")

        var id = intent.getStringExtra("id")
        val gameText = intent.getStringExtra("gameText")
        var score = intent.getIntExtra("score", 0)

        var btnWhere = findViewById<Button>(R.id.btnSaveWhere)

        CB0 = findViewById(R.id.CB0)
        CB1 = findViewById(R.id.CB1)
        CB2 = findViewById(R.id.CB2)
        CB3 = findViewById(R.id.CB3)
        CB4 = findViewById(R.id.CB4)
        CB5 = findViewById(R.id.CB5)

        CB0.isVisible = true
        CB1.isVisible = true
        CB2.isVisible = true
        CB3.isVisible = true
        CB4.isVisible = true
        CB5.isVisible = true

        if (gameText != null) {
            val gameTextList = gameText.split(" ") //List
            var count = gameTextList.count()
            if (count == 1) {
                CB0.setText(gameTextList[0])
                CB1.isVisible = false
                CB2.isVisible = false
                CB3.isVisible = false
                CB4.isVisible = false
                CB5.isVisible = false
            } else if (count == 2) {
                CB0.setText(gameTextList[0])
                CB1.setText(gameTextList[1])
                CB2.isVisible = false
                CB3.isVisible = false
                CB4.isVisible = false
                CB5.isVisible = false
            } else if (count == 3) {
                CB0.setText(gameTextList[0])
                CB1.setText(gameTextList[1])
                CB2.setText(gameTextList[2])
                CB3.isVisible = false
                CB4.isVisible = false
                CB5.isVisible = false
            } else if (count == 4) {
                CB0.setText(gameTextList[0])
                CB1.setText(gameTextList[1])
                CB2.setText(gameTextList[2])
                CB3.setText(gameTextList[3])
                CB4.isVisible = false
                CB5.isVisible = false
            } else if (count == 5) {
                CB0.setText(gameTextList[0])
                CB1.setText(gameTextList[1])
                CB2.setText(gameTextList[2])
                CB3.setText(gameTextList[3])
                CB4.setText(gameTextList[4])
                CB5.isVisible = false
            } else {
                CB0.setText(gameTextList[0])
                CB1.setText(gameTextList[1])
                CB2.setText(gameTextList[2])
                CB3.setText(gameTextList[3])
                CB4.setText(gameTextList[4])
                CB5.setText(gameTextList[5])
            }

            var locationList = mutableListOf<String>()

            btnWhere.setOnClickListener {
                if (CB0.isChecked) {
                    locationList.add(CB0.getText().toString())
                }
                if (CB1.isChecked) {
                    locationList.add(CB1.getText().toString())
                }
                if (CB2.isChecked) {
                    locationList.add(CB2.getText().toString())
                }
                if (CB3.isChecked) {
                    locationList.add(CB3.getText().toString())
                }
                if (CB4.isChecked) {
                    locationList.add(CB4.getText().toString())
                }
                if (CB5.isChecked) {
                    locationList.add(CB5.getText().toString())
                }

                val BASE_URL = "http://3.35.88.89:8080"
                var locationString = locationList.joinToString(" ")

                var gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                val api = retrofit.create(diaryAPI::class.java)
                val callGetGameText = api.getGameText((gameText(locationString)))
                callGetGameText.enqueue(object : Callback<gameText> {
                    override fun onResponse(call: Call<gameText>, response: Response<gameText>) {
                        score = score + 1
                        val BASE_URL = "http://3.35.88.89:8080"

                        var gson = GsonBuilder()
                            .setLenient()
                            .create()

                        val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()

                        val api = retrofit.create(memberAPI::class.java)
                        val callSaveScore = api.saveScore2(memberInfo("$id", "0", score, "$date"))

                        callSaveScore.enqueue(object : Callback<memberInfo> {
                            override fun onResponse(call: Call<memberInfo>, response: Response<memberInfo>) {
                                var intent = Intent(this@LocationActivity, ResultActivity::class.java)
                                intent.putExtra("id", id)
                                intent.putExtra("score", score)
                                startActivity(intent)
                            }
                            override fun onFailure(call: Call<memberInfo>, t: Throwable) {
                                Log.d(ContentValues.TAG, "실패: $t")
                            }
                        })
                        Log.d(ContentValues.TAG, "성공: ${response.raw()}")
                    }

                    override fun onFailure(call: Call<gameText>, t: Throwable) {
                        Log.d(ContentValues.TAG, "실패: $t")
                    }
                })
            }
        }
    }
}