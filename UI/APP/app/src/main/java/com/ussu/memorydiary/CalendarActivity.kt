package com.ussu.memorydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast

class CalendarActivity : AppCompatActivity() {
    private lateinit var calendar: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val id = intent.getStringExtra("id")

        //선택한 날짜 Toast + 일기 화면으로 이동
        calendar = findViewById(R.id.calendarView)

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var month = month + 1
            val date = "$year-$month-$dayOfMonth"
            var intent = Intent(this@CalendarActivity, DiaryActivity::class.java)
            intent.putExtra("id", "$id")
            intent.putExtra("date", "$date")
            startActivity(intent)
        }

    }

}