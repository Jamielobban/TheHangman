package com.example.hangmannewgame

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.view.LayoutInflater

class RankingAdapter(private val userList : ArrayList<UserInfo>) : RecyclerView.Adapter<RankingAdapter.MyViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.userName.text = currentitem.name
        holder.score2.text = currentitem.score.toString()
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val userName : TextView = itemView.findViewById(R.id.textView)
        val score2 : TextView = itemView.findViewById(R.id.textViewScores)
    }
}