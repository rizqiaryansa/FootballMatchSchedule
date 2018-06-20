package com.rizqiaryansa.footballmatchschedule.view.view.next

import com.rizqiaryansa.footballmatchschedule.view.model.Event

interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>?)
    fun errorMessage(message: String?)
}