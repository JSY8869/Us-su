package com.ussu.memorydiary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ussu.memorydiary.databinding.ActivityTextSizeBinding

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "TextSizeActivity"
    }

    private lateinit var viewBinding: ActivityTextSizeBinding
    private var currentTheme = R.style.Theme_App_Medium
    private lateinit var pref: DefaultPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = DefaultPreferenceManager(this)

        val textSize = pref.getTextSize()
        currentTheme = getAppTheme(textSize)
        setTheme(currentTheme)

        viewBinding = ActivityTextSizeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()
    }

    open fun initView() {}

    override fun onResume() {
        super.onResume()

        val textSize = pref.getTextSize()
        val settingTheme = getAppTheme(textSize)

        if (currentTheme != settingTheme) {
            recreate()
        }
    }

    fun getAppTheme(textSize: Int) =
        when (textSize) {
            0 -> R.style.Theme_App_Small
            1 -> R.style.Theme_App_Medium
            2 -> R.style.Theme_App_Large
            else -> R.style.Theme_App_Medium
        }
}