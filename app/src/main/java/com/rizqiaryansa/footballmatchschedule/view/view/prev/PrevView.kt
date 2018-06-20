package com.rizqiaryansa.footballmatchschedule.view.view.prev

import com.rizqiaryansa.footballmatchschedule.view.model.Event

interface PrevView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}