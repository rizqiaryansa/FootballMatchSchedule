package com.rizqiaryansa.footballmatchschedule.view.presenter

import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import com.rizqiaryansa.footballmatchschedule.view.model.EventResponse
import com.rizqiaryansa.footballmatchschedule.view.view.next.NextView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextPresenter (private val view: NextView,
                     private val apiRequest: ApiRequest,
                     private val gson: Gson) {

    fun getEventList(match: String?) {
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSchedule(match)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().match)
            view.hideLoading()
        }
    }
}