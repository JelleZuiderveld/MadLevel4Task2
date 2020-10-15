package com.example.madlevel4task2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.RockPaper

class HistoryAdapter(private val rockPaper : List<RockPaper>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context: Context = itemView.context.applicationContext
        private val tvResultPast: TextView = itemView.findViewById(R.id.tvResultPast)

        fun databind(rockPaper: RockPaper){
            when(rockPaper.result){
                RockPaper.Result.LOSS -> tvResultPast.text = context.getString(R.string.loss)
                RockPaper.Result.DRAW -> tvResultPast.text = context.getString(R.string.draw)
                RockPaper.Result.WIN -> tvResultPast.text = context.getString(R.string.win)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return rockPaper.size
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.databind(rockPaper[position])
    }
}