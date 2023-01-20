package com.example.footballmatchtrackertest.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballmatchtrackertest.R
import com.example.footballmatchtrackertest.data.api.RetrofitInstance
import com.example.footballmatchtrackertest.databinding.MatchListItemBinding
import com.example.footballmatchtrackertest.data.model.Data
import kotlinx.android.synthetic.main.match_list_item.view.*

class MatchesAdapter(val matchList:List<Data>, val context: Context, val listener:MatchListener) : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {
    inner class MatchViewHolder(val view:View): RecyclerView.ViewHolder(view) {
        var bindind = MatchListItemBinding.bind(view)
        fun bind(match : Data) {
            bindind.tvDate.text = match.time.date
            bindind.tvLeague.text = match.league.name
            bindind.tvHomeTeam.text = "${match.teams.home.name}"
            bindind.tvAwayTeam.text = "${match.teams.away.name}"
            val urlHomeTeam = match.teams.home.img
            Glide
                .with(context)
                .load(urlHomeTeam)
                .placeholder(R.drawable.ic_launcher_background)
                .into(bindind.ivHomeTeam)

            val urlAwayTeam = match.teams.away.img
            Glide
                .with(context)
                .load(urlAwayTeam)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(bindind.ivAwayTeam)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_list_item, parent,false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
        holder.view.ivAdd.setOnClickListener{
            listener.onClick(matchList[position])
        }
    }

    override fun getItemCount(): Int {
       return matchList.size
    }

    interface MatchListener {
        fun onClick(match : Data)
    }
}