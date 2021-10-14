package com.ussu.memorydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun clickDiary(view: View) {
        val id = intent.getStringExtra("id")
        var intent = Intent(this@HomeActivity, CalendarActivity::class.java)
        intent.putExtra("id", "$id")
        startActivity(intent)
    }

    fun clickGame(view: View) {
        val id = intent.getStringExtra("id")
        var intent = Intent(this@HomeActivity, GameCalendarActivity::class.java)
        intent.putExtra("id", "$id")
        startActivity(intent)
    }

    fun clickSetting(view: View) {
        var intent = Intent(this@HomeActivity, TextSizeActivity::class.java)
        startActivity(intent)
    }
}