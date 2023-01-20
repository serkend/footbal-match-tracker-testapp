package com.example.footballmatchtrackertest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballmatchtrackertest.R
import com.example.footballmatchtrackertest.databinding.MatchListItemBinding
import com.example.footballmatchtrackertest.data.model.Data
import kotlinx.android.synthetic.main.match_list_item.view.*

class MatchesAdapter(val matchList: List<Data>, val context: Context, val listener: MatchListener) :
    RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {
    inner class MatchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var binding = MatchListItemBinding.bind(view)
        fun bind(match: Data) {
            binding.tvDate.text = match.time.date
            binding.tvLeague.text = match.league.name
            binding.tvStatus.text = match.status_name
            binding.tvTime.text = match.time.time
            binding.tvHomeTeam.text = "${match.teams.home.name}"
            binding.tvAwayTeam.text = "${match.teams.away.name}"
            val urlHomeTeam = match.teams.home.img
            Glide
                .with(context)
                .load(urlHomeTeam)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivHomeTeam)

            val urlAwayTeam = match.teams.away.img
            Glide
                .with(context)
                .load(urlAwayTeam)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivAwayTeam)

            if(match.status != 0) {
                binding.tvHomeScores.visibility = View.VISIBLE
                binding.tvAwayScores.visibility = View.VISIBLE

                binding.tvHomeScores.text = match.scores.home_score
                binding.tvAwayScores.text = match.scores.away_score
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.match_list_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
        holder.view.ivAdd.setOnClickListener {
            listener.onClick(matchList[position])
        }
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    interface MatchListener {
        fun onClick(match: Data)
    }
}