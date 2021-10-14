package com.ussu.memorydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox

class AgreeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agree)

    }

    fun onCheckboxClicked(view: View) {

        var intent = Intent(this@AgreeActivity, LoginActivity::class.java)
        startActivity(intent)

    }
}