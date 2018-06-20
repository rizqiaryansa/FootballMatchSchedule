package com.rizqiaryansa.footballmatchschedule.view.view.next

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rizqiaryansa.footballmatchschedule.R
import com.rizqiaryansa.footballmatchschedule.view.model.Event
import com.rizqiaryansa.footballmatchschedule.view.view.EventMatchUI
import com.rizqiaryansa.footballmatchschedule.view.view.detail.DetailActivity
import org.jetbrains.anko.*

class NextAdapter(private val matchs: List<Event>) :
        RecyclerView.Adapter<NextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextViewHolder {
        return NextViewHolder(EventMatchUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: NextViewHolder, position: Int) {
        holder.bindItem(matchs[position])
    }

}

class NextViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val timeSchedule: TextView = view.find(R.id.time_schedule)
    private val homeTeam: TextView = view.find(R.id.tvHomeTeam)
    private val homeScore: TextView = view.find(R.id.tvHomeScore)
    private val awayScore: TextView = view.find(R.id.tvAwayScore)
    private val awayTeam: TextView = view.find(R.id.tvAwayTeam)


    fun bindItem(matchs: Event) {
        timeSchedule.text = matchs.dateEvent
        homeTeam.text = matchs.strHomeTeam
        homeScore.text = matchs.intHomeScore
        awayScore.text = matchs.intAwayScore
        awayTeam.text = matchs.strAwayTeam

        val ctx = itemView.context

        itemView.setOnClickListener {
            ctx.startActivity<DetailActivity>(
                    ctx.getString(R.string.item_eventdetail_id) to matchs.idEvent,
                    ctx.getString(R.string.item_home_id) to matchs.idHomeTeam,
                    ctx.getString(R.string.item_away_id) to matchs.idAwayTeam)
        }
    }
}