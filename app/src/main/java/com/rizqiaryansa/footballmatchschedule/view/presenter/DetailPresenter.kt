package com.rizqiaryansa.footballmatchschedule.view.presenter

import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import com.rizqiaryansa.footballmatchschedule.view.model.EventResponse
import com.rizqiaryansa.footballmatchschedule.view.model.TeamResponse
import com.rizqiaryansa.footballmatchschedule.view.view.detail.DetailView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRequest: ApiRequest,
                      private val gson: Gson) {

    fun getEventDetail(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()

        doAsync {
            val eventDetail = gson.fromJson(apiRequest.doRequest(TheSportDBApi.
                    getDetailEvent(idEvent)),
                    EventResponse::class.java)
            val badgeHome = gson.fromJson(apiRequest.doRequest(TheSportDBApi.
                    getHomeBadge(idHomeTeam)), TeamResponse::class.java)
            val badgeAway = gson.fromJson(apiRequest.doRequest(TheSportDBApi.
                    getAwayBadge(idAwayTeam)),
                    TeamResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showEventList(eventDetail.match, badgeHome.teams, badgeAway.teams)
            }
        }
    }
}