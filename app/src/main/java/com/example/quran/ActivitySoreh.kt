package com.example.quran


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_soreh.*
import java.io.BufferedReader


lateinit var adapterAyeh: CustomAdapterAyeh_Tarjomeh

class ActivitySoreh : AppCompatActivity() {
    val myarrListfa = arrayListOf<String>()
    val myarrList = arrayListOf<String>()
    lateinit var message: String
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soreh)
        readSoreh()
        readTarjomeh()
        val recyclerView = findViewById(R.id.recyclerViewSoreh) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val users = ArrayList<data_ayeh_tarjomeh>()
        for (i in myarrList.indices)
            users.add(data_ayeh_tarjomeh(myarrList[i], myarrListfa[i]))

        adapterAyeh = CustomAdapterAyeh_Tarjomeh(this,users)

        recyclerView.adapter = adapterAyeh
        recyclerView.scrollToPosition(ayeh.toInt())

    }


    //*********************************
  public  fun play() {
        if ((::mediaPlayer.isInitialized) && (mediaPlayer.isPlaying)) {
            FAB_play.setImageResource(android.R.drawable.ic_media_play)
            mediaPlayer.stop()
            mediaPlayer.reset()
             return
        }
        var url =
            "http://www.everyayah.com//data//AbdulSamad_64kbps_QuranExplorer.Com//" // your URL here
        url = url + String.format("%03d%03d.mp3", soreh + 1, ayeh + 1)
        Log.d("my", url)

        try {
            mediaPlayer = MediaPlayer().apply {
                //setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(url)
                prepareAsync() // might take long! (for buffering, etc)
                Log.d("my", "prepare")
                FAB_play.setImageResource(android.R.drawable.ic_menu_more)
            }
        } catch (e: Exception) {
            Log.d("my", e.toString())
        }
        mediaPlayer.setOnPreparedListener {
            Log.d("my", "start")
            FAB_play.setImageResource(android.R.drawable.ic_media_pause)
            mediaPlayer.start()
        }


        mediaPlayer.setOnCompletionListener(MediaPlayer.OnCompletionListener {

            if (ayeh < myarrList.size - 1) {
                Toast.makeText(this, "next ...", Toast.LENGTH_SHORT).show()
                ayeh++
                recyclerViewSoreh.adapter?.notifyDataSetChanged()
                recyclerViewSoreh.post(Runnable {
                    recyclerViewSoreh.smoothScrollToPosition(
                        ayeh.toInt()
                    )
                    //call smooth scroll
                })


                play()
            } else {
                FAB_play.setImageResource(android.R.drawable.ic_media_play)
                Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //*********************************

    fun readSoreh() {
        try {
            message = (soreh + 1).toString()
            val fname = getResources().openRawResource(
                getResources().getIdentifier(
                    "s" + message,
                    "raw", getPackageName()
                )
            );
            val ff = BufferedReader(fname.reader())
            var thisLine = ff.readLine()
            while (thisLine != null) {
                myarrList.add(thisLine)
                thisLine = ff.readLine()
            }
            ff.close()
        } catch (e: java.lang.Exception) {
        }
    }

    //*********************************
    private fun readTarjomeh() {
        try {
            message = (soreh + 1).toString()
            val fname = getResources().openRawResource(
                getResources().getIdentifier(
                    "f" + message,
                    "raw", getPackageName()
                )
            );
            val ff = BufferedReader(fname.reader())
            var thisLinefa = ff.readLine()
            while (thisLinefa != null) {
                myarrListfa.add(thisLinefa)
                thisLinefa = ff.readLine()
            }
            ff.close()
        } catch (e: java.lang.Exception) {
        }
    }


    //*********************************
    fun fabclick(view: View) {
        play()
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

    override fun onStop() {

        super.onStop()
        FAB_play.setImageResource(android.R.drawable.ic_media_play)
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
    }

}