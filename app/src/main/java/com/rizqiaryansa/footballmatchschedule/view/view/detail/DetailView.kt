package com.rizqiaryansa.footballmatchschedule.view.view.detail

import com.rizqiaryansa.footballmatchschedule.view.model.Event
import com.rizqiaryansa.footballmatchschedule.view.model.Team

interface DetailView {
    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Event>, home: List<Team>, away: List<Team>)
}