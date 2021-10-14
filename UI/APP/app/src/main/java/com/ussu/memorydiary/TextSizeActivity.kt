package com.ussu.memorydiary

import android.os.Bundle
import android.widget.SeekBar
import com.ussu.memorydiary.databinding.ActivityTextSizeBinding

class TextSizeActivity : BaseActivity() {
    companion object {
        private const val TAG = "TextSizeActivity"
    }

    private lateinit var viewBinding: ActivityTextSizeBinding
    private var currentTheme = R.style.Theme_App_Medium
    private lateinit var pref: DefaultPreferenceManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initView()
    }

    override fun initView() {
        pref = DefaultPreferenceManager(this)
        viewBinding = ActivityTextSizeBinding.inflate(layoutInflater)

        viewBinding.textsizeSeekbar.progress = pref.getTextSize()
        viewBinding.textsizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    pref.setTextSize(seekBar.progress)
                    if (currentTheme != getAppTheme(seekBar.progress)) {
                        recreate()
                    }
                }

            }
        })
    }}