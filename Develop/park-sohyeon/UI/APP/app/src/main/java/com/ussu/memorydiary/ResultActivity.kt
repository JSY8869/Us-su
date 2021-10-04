package com.ussu.memorydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.ussu.memorydiary.R

class ResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var scoreImage = findViewById<ImageView>(R.id.scoreImage)

        //애니메이션 정의
        val scaleX2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_x2)
        val scaleX3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_x3)
        val scaleX4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_x4)
        val scaleX5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_x5)

        //사진 선택
        //scoreImage.setImageResource(R.drawable.leaf)

        //애니메이션 설정
        //scoreImage.startAnimation(scaleX2)

        val score = intent.getIntExtra("score", 0)

        Toast.makeText(this, "$score", Toast.LENGTH_LONG).show()

        if (score in 1..2) {
            scoreImage.setImageResource(R.drawable.leaf)
            scoreImage.startAnimation(scaleX2)
        } else if (score in 3..4) {
            scoreImage.setImageResource(R.drawable.leaf)
            scoreImage.startAnimation(scaleX3)
        } else if (score in 5..7) {
            scoreImage.setImageResource(R.drawable.leaf)
            scoreImage.startAnimation(scaleX4)
        } else if (score in 8..9) {
            scoreImage.setImageResource(R.drawable.leaf)
            scoreImage.startAnimation(scaleX5)
        } else if (score in 10..29) {
            scoreImage.setImageResource(R.drawable.flower)
            scoreImage.startAnimation(scaleX2)
        } else if (score in 30..59) {
            scoreImage.setImageResource(R.drawable.flower)
            scoreImage.startAnimation(scaleX3)
        } else if (score in 60..89) {
            scoreImage.setImageResource(R.drawable.flower)
            scoreImage.startAnimation(scaleX4)
        } else if (score in 90..99) {
            scoreImage.setImageResource(R.drawable.flower)
            scoreImage.startAnimation(scaleX5)
        } else if (score in 100..199) {
            scoreImage.setImageResource(R.drawable.tree)
            scoreImage.startAnimation(scaleX2)
        } else if (score in 200..299) {
            scoreImage.setImageResource(R.drawable.tree)
            scoreImage.startAnimation(scaleX3)
        } else if (score in 300..399) {
            scoreImage.setImageResource(R.drawable.tree)
            scoreImage.startAnimation(scaleX4)
        } else if (score in 400..500) {
            scoreImage.setImageResource(R.drawable.tree)
            scoreImage.startAnimation(scaleX5)
        } else {
            Toast.makeText(this, "score가 범위 안에 없습니다.", Toast.LENGTH_LONG).show()
        }
    }

    fun btnHome(view: View) {
        var id = intent.getStringExtra("id")
        var intent = Intent(this@ResultActivity, HomeActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}