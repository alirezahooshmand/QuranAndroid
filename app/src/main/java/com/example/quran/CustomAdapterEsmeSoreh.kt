package com.example.quran

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CustomAdapterSoreh(val userList: ArrayList<data_esme_soreh>) :
    RecyclerView.Adapter<CustomAdapterSoreh.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_soreh, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position], position)


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: data_esme_soreh, mypostion: Int) {
            val textViewName = itemView.findViewById(R.id.textViewSoreh) as TextView
            textViewName.text = user.esme

            if (soreh == mypostion.toLong())
                textViewName.setTextColor(Color.BLUE)
            else
                textViewName.setTextColor(Color.BLACK)

            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()
                Toast.makeText(
                    itemView.context,
                    user.esme + " \n position:" + position,
                    Toast.LENGTH_SHORT
                ).show()
                if (soreh != position.toLong())
                    ayeh = 0
                soreh = position.toLong()
                adapterSoreh?.notifyDataSetChanged()
                val myintent = Intent(myContext, ActivitySoreh::class.java)
                myContext.startActivity(myintent)
            }

        }
    }
}