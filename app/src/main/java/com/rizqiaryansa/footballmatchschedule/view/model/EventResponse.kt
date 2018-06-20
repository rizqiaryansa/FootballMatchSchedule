package com.rizqiaryansa.footballmatchschedule.view.model

import com.google.gson.annotations.SerializedName

data class EventResponse(
        @SerializedName("events")
        val match: List<Event>
)