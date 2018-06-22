package com.rizqiaryansa.footballmatchschedule.view.view.favorites

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rizqiaryansa.footballmatchschedule.R
import com.rizqiaryansa.footballmatchschedule.view.db.FavoriteDB
import com.rizqiaryansa.footballmatchschedule.view.view.EventMatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class FavoritesAdapter(private val favorite: List<FavoriteDB>,
                       private val listener: (FavoriteDB) -> Unit):
        RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(EventMatchUI().createView(AnkoContext.
                create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size
}



class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val timeSchedule: TextView = view.find(R.id.time_schedule)
    private val homeTeam: TextView = view.find(R.id.tvHomeTeam)
    private val homeScore: TextView = view.find(R.id.tvHomeScore)
    private val awayScore: TextView = view.find(R.id.tvAwayScore)
    private val awayTeam: TextView = view.find(R.id.tvAwayTeam)

    fun bindItem(favorite: FavoriteDB, listener: (FavoriteDB) -> Unit) {

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(favorite.eventTime)
        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        timeSchedule.text = dateEvent.toString()
        homeTeam.text = favorite.homeTeam
        homeScore.text = favorite.homeScore
        awayScore.text = favorite.awayScore
        awayTeam.text = favorite.awayTeam

        itemView.onClick {
            listener(favorite)
        }
    }
}