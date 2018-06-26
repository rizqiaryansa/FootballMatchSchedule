package league

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.rizqiaryansa.footballmatchschedule.R
import com.rizqiaryansa.footballmatchschedule.R.id.*
import com.rizqiaryansa.footballmatchschedule.view.view.LeagueActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeagueActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(LeagueActivity::class.java)
    private val wait: Long = 3000

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(wait)
        onView(withId(rvPrevEvent)).check(matches(isDisplayed()))
        onView(withId(rvPrevEvent)).perform(RecyclerViewActions.
                scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(withId(rvPrevEvent)).perform(
                RecyclerViewActions.actionOnItemAtPosition<
                        RecyclerView.ViewHolder>(8, click()))
    }

    @Test
    fun testAppUIBehaviour() {

        //SwipeRefresh
        Thread.sleep(wait)
        onView(withId(swipeRefresh)).perform(swipeDown())

        //
        onView(withId(container)).check(matches(isDisplayed()))
        onView(withId(navigation_prev_league)).check(matches(isDisplayed()))
        onView(withId(navigation_next_league)).check(matches(isDisplayed()))
        onView(withId(navigation_favorites)).check(matches(isDisplayed()))

        Thread.sleep(wait)
        onView(withId(rvPrevEvent)).check(matches(isDisplayed()))
        onView(withId(rvPrevEvent))
                .perform(RecyclerViewActions.actionOnItemAtPosition
                <RecyclerView.ViewHolder>(5, click()));

        Thread.sleep(wait)
        onView(withId(lyEventDetail)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        pressBack()

        Thread.sleep(wait)

        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_favorites)).perform(click())
    }
}