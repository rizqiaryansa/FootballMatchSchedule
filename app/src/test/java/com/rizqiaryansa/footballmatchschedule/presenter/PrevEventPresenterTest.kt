package com.rizqiaryansa.footballmatchschedule.presenter

import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.TestContextProvider
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import com.rizqiaryansa.footballmatchschedule.view.model.Event
import com.rizqiaryansa.footballmatchschedule.view.model.EventResponse
import com.rizqiaryansa.footballmatchschedule.view.presenter.PrevPresenter
import com.rizqiaryansa.footballmatchschedule.view.view.prev.PrevView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PrevEventPresenterTest {
    @Mock
    private lateinit var view: PrevView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: PrevPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetPrevEvent() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val paramEvent = "eventspastleague"

        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSchedule(paramEvent)),
                EventResponse::class.java)).thenReturn(response)

        presenter.getEventList(paramEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(events)
        Mockito.verify(view).hideLoading()
    }
}