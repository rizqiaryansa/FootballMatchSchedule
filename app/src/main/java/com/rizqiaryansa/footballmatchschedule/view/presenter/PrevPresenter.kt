package com.rizqiaryansa.footballmatchschedule.view.presenter

import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import com.rizqiaryansa.footballmatchschedule.view.model.EventResponse
import com.rizqiaryansa.footballmatchschedule.view.util.CoroutineContextProvider
import com.rizqiaryansa.footballmatchschedule.view.view.prev.PrevView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PrevPresenter(private val view: PrevView,
                    private val apiRequest: ApiRequest,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEventList(match: String?) {
        view.showLoading()
        async(context.main) {
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