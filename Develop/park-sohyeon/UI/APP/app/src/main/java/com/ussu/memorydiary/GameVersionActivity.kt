package com.ussu.memorydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GameVersionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_version)
    }

    fun goV1(view: View) {
        val id = intent.getStringExtra("id")
        val date = intent.getStringExtra("date")
        var intent = Intent(this@GameVersionActivity, GameActivity::class.java)
        intent.putExtra("id", "$id")
        intent.putExtra("date", date)
        startActivity(intent)
    }

    fun goV2(view: View) {
        val id = intent.getStringExtra("id")
        val date = intent.getStringExtra("date")
        var intent = Intent(this@GameVersionActivity, Game2Activity::class.java)
        intent.putExtra("id", "$id")
        intent.putExtra("date", date)
        startActivity(intent)
    }
}