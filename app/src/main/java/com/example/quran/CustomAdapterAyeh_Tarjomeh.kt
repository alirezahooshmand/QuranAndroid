package com.example.quran

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
//<========init
class CustomAdapterAyeh_Tarjomeh(activitySoreh:ActivitySoreh,val userList: ArrayList<data_ayeh_tarjomeh>) :
    RecyclerView.Adapter<CustomAdapterAyeh_Tarjomeh.ViewHolder>() {
    lateinit var myactivitySoreh:ActivitySoreh
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_ayeh, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position], position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: data_ayeh_tarjomeh, mypostion: Int) {
            val textViewAyeh = itemView.findViewById(R.id.textViewAyeh) as TextView
            val textViewTarjomeh = itemView.findViewById(R.id.textViewTarjomehAyeh) as TextView
            textViewAyeh.text = user.Ayeh
            textViewTarjomeh.text = user.Tarjomeh

            if (ayeh == mypostion.toLong())
                textViewAyeh.setTextColor(Color.BLUE)
            else
                textViewAyeh.setTextColor(Color.BLACK)

            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()
                Toast.makeText(
                    itemView.context,
                    user.Ayeh + " \n position:" + position,
                    Toast.LENGTH_SHORT
                ).show()

                ayeh = position.toLong()
                adapterAyeh?.notifyDataSetChanged()


            }

        }
    }
}

