package com.rizqiaryansa.footballmatchschedule.view.api

import com.rizqiaryansa.footballmatchschedule.BuildConfig

object TheSportDBApi {

    private const val idLeague = ".php?id=4328"
    private const val strLookUpTeam = "lookupteam.php?id="
    private const val strLookUpEvent = "lookupevent.php?id="

    fun getSchedule(eventLeague: String?): String {
        return BuildConfig.BASE_URL + eventLeague + idLeague
    }

    fun getDetailEvent(idLeague: String?) : String {
        return BuildConfig.BASE_URL + strLookUpEvent + idLeague
    }

    fun getHomeBadge(idHome: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idHome
    }

    fun getAwayBadge(idAway: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idAway
    }
}