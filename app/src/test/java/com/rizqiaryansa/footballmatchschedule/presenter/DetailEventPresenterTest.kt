package com.rizqiaryansa.footballmatchschedule.presenter

import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.TestContextProvider
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import com.rizqiaryansa.footballmatchschedule.view.model.Event
import com.rizqiaryansa.footballmatchschedule.view.model.EventResponse
import com.rizqiaryansa.footballmatchschedule.view.model.Team
import com.rizqiaryansa.footballmatchschedule.view.model.TeamResponse
import com.rizqiaryansa.footballmatchschedule.view.presenter.DetailPresenter
import com.rizqiaryansa.footballmatchschedule.view.view.detail.DetailView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailEventPresenterTest {
    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetNextEvent() {
        val events: MutableList<Event> = mutableListOf()
        val home: MutableList<Team> = mutableListOf()
        val away: MutableList<Team> = mutableListOf()
        val response = EventResponse(events)
        val homeResponse = TeamResponse(home)
        val awayResponse = TeamResponse(away)
        val idEvent = "526916"
        val idHomeTeam = "134778"
        val idAwayTeam = "133613"

        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                EventResponse::class.java)).thenReturn(response)
        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getHomeBadge(idHomeTeam)),
                TeamResponse::class.java)).thenReturn(homeResponse)
        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getAwayBadge(idAwayTeam)),
                TeamResponse::class.java)).thenReturn(awayResponse)

        presenter.getEventDetail(idEvent, idHomeTeam, idAwayTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(events, home, away)
        Mockito.verify(view).hideLoading()
    }
}