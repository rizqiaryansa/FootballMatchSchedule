package com.rizqiaryansa.footballmatchschedule.view.view.prev

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rizqiaryansa.footballmatchschedule.R
import com.rizqiaryansa.footballmatchschedule.R.color.colorBlack
import com.rizqiaryansa.footballmatchschedule.R.color.colorPrimary
import com.rizqiaryansa.footballmatchschedule.R.string.item_away_id
import com.rizqiaryansa.footballmatchschedule.R.string.item_home_id
import com.rizqiaryansa.footballmatchschedule.view.model.Event
import com.rizqiaryansa.footballmatchschedule.view.view.EventMatchUI
import com.rizqiaryansa.footballmatchschedule.view.view.detail.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.text.SimpleDateFormat
import java.util.*

class PrevAdapter(private val matchs: List<Event>) :
        RecyclerView.Adapter<PrevViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevViewHolder {
        return PrevViewHolder(EventMatchUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: PrevViewHolder, position: Int) {
        holder.bindItem(matchs[position])
    }

}

class PrevViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val timeSchedule: TextView = view.find(R.id.time_schedule)
    private val homeTeam: TextView = view.find(R.id.tvHomeTeam)
    private val homeScore: TextView = view.find(R.id.tvHomeScore)
    private val awayScore: TextView = view.find(R.id.tvAwayScore)
    private val awayTeam: TextView = view.find(R.id.tvAwayTeam)


    fun bindItem(matchs: Event) {

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(matchs.dateEvent)
        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        timeSchedule.text = dateEvent.toString()
        homeTeam.text = matchs.strHomeTeam
        homeScore.text = matchs.intHomeScore
        awayScore.text = matchs.intAwayScore
        awayTeam.text = matchs.strAwayTeam

        val ctx = itemView.context

        itemView.setOnClickListener {
            ctx.startActivity<DetailActivity>(
                    ctx.getString(R.string.item_eventdetail_id) to matchs.idEvent,
                    ctx.getString(item_home_id) to matchs.idHomeTeam,
                    ctx.getString(item_away_id) to matchs.idAwayTeam)
        }
    }
}