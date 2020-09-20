package com.example.quran

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ActivityAbout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTitle("About")
    }

    fun aboutMailtoClick(view: View) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("alireza.houshmand@gmail.com"))
        i.putExtra(Intent.EXTRA_SUBJECT, "Qurqn for android")
        i.putExtra(Intent.EXTRA_TEXT, "در باره برنامه ....")
        try {
            startActivity(Intent.createChooser(i, "ارسال ایمیل به ...."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this@ActivityAbout,
                "There are no email clients installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}