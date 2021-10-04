package com.ussu.memorydiary

import android.content.Context
import android.preference.PreferenceManager

class DefaultPreferenceManager(private var context: Context) {
    companion object {
        private const val WORD_TEXT_SIZE = "text_size"
    }

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = sharedPreferences.edit()

    fun setTextSize(size: Int) {
        editor.putInt(WORD_TEXT_SIZE, size).commit()
    }

    fun getTextSize() = sharedPreferences.getInt(WORD_TEXT_SIZE, 1)
}