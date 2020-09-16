package com.example.quran

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

private const val TITLE_TAG = "settingsActivityTitle"

class SettingsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setTitle(TITLE_TAG)

    }
}