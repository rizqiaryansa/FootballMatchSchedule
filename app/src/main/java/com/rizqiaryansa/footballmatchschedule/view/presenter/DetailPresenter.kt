package com.rizqiaryansa.footballmatchschedule.view.presenter

import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import com.rizqiaryansa.footballmatchschedule.view.model.EventResponse
import com.rizqiaryansa.footballmatchschedule.view.model.TeamResponse
import com.rizqiaryansa.footballmatchschedule.view.view.detail.DetailView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val view: DetailView,
                      private val apiRequest: ApiRequest,
                      private val gson: Gson) {

    fun getEventDetail(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()

        async(UI) {
            val eventDetail = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                        EventResponse::class.java)
            }
            val badgeHome = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getHomeBadge(idHomeTeam)),
                        TeamResponse::class.java)
            }
            val badgeAway =  bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getAwayBadge(idAwayTeam)),
                        TeamResponse::class.java)
            }
            view.showEventList(eventDetail.await().match, badgeHome.await().teams,
                    badgeAway.await().teams)
            view.hideLoading()
        }
    }
}