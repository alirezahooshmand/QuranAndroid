package com.example.quran

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader

lateinit var adapterSoreh: CustomAdapterSoreh
lateinit var myContext: Context
var soreh: Long = 0
var ayeh: Long = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_esme_soreh)
        myContext=this

        readData()

        val recyclerView = findViewById(R.id.recyclerView_esmeSoreh) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //************read list of soure
        val myarrList = arrayListOf<data_esme_soreh>()
        try {
            val ff = BufferedReader(resources.openRawResource(R.raw.qlist).reader())
            var thisLine = ff.readLine()
            while (thisLine != null) {
                myarrList.add(data_esme_soreh(thisLine))
                thisLine = ff.readLine()
            }
        } catch (e: java.lang.Exception) {
        }
//**********************

        adapterSoreh = CustomAdapterSoreh(myarrList)

        recyclerView.adapter = adapterSoreh
        recyclerView.scrollToPosition(soreh.toInt())
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflat = menuInflater
        inflat.inflate(R.menu.my_options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.setting) {
//            Toast.makeText(this, "setting click", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, SettingsActivity::class.java))
//        } else
        if (item.itemId == R.id.about) {
            Toast.makeText(this, "about click", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ActivityAbout::class.java))
        }
//            else
//                if (item.itemId == R.id.help) {
//                    Toast.makeText(this, "Help Click ....", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this, ActivityHelp::class.java))
//                }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

    fun saveData() {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putLong("ayeh", ayeh)
        editor.putLong("soreh", soreh)
        editor.commit()
    }

    fun readData() {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        ayeh = sharedPreference.getLong("ayeh", 0L)
        soreh = sharedPreference.getLong("soreh", 0L)
    }
}