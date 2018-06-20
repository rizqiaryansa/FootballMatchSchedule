package com.rizqiaryansa.footballmatchschedule.view.presenter

import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import com.rizqiaryansa.footballmatchschedule.view.model.EventResponse
import com.rizqiaryansa.footballmatchschedule.view.view.next.NextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextPresenter (private val view: NextView,
                     private val apiRequest: ApiRequest,
                     private val gson: Gson) {

    fun getEventList(match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRequest.doRequest(TheSportDBApi.
                    getSchedule(match)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.match)
            }
        }
    }
}