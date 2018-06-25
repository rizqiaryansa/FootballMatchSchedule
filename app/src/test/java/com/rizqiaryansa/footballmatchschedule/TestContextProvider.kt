package com.rizqiaryansa.footballmatchschedule

import com.rizqiaryansa.footballmatchschedule.view.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}