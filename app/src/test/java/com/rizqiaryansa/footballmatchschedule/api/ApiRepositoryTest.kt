package com.rizqiaryansa.footballmatchschedule.api

import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.api.TheSportDBApi
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {
    @Test
    fun testPreEventApi() {
        val apiRepository = mock(ApiRequest::class.java)
        val url = TheSportDBApi.getPrevEvent("4328")
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testNextEventApi() {
        val apiRequest = mock(ApiRequest::class.java)
        val url = TheSportDBApi.getNextEvent("4328")
        apiRequest.doRequest(url)
        verify(apiRequest).doRequest(url)
    }
}