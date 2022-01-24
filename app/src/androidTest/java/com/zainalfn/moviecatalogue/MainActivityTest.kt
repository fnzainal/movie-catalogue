package com.zainalfn.moviecatalogue

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.zainalfn.moviecatalogue.data.source.local.CatalogueData
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.ui.MainActivity
import com.zainalfn.moviecatalogue.util.DummyData
import com.zainalfn.moviecatalogue.util.EspressoIdlingResource
import com.zainalfn.moviecatalogue.util.toReadableDate
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DummyData.getDetailMovie().first()
    private val dummyTvShow = DummyData.getDetailTvShow().first()
    private val emptyData = emptyList<CatalogueData>()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(
            EspressoIdlingResource.getEspressoIdlingResource())
    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun clickAbout() {
        onView(withId(R.id.action_about)).check(matches(isDisplayed()))
        onView(withId(R.id.action_about)).check(matches(isClickable()))
        onView(withId(R.id.action_about)).perform(click())
        onView(withText(R.string.action_about)).check(matches(isDisplayed()))
        onView(withText(R.string.back)).check(matches(isDisplayed()))
        onView(withText(R.string.back)).check(matches(isClickable()))
        onView(withText(R.string.back)).perform(click())
    }

    @Test
    fun clickOpenSource() {
        onView(withId(R.id.action_open_source)).check(matches(isDisplayed()))
        onView(withId(R.id.action_open_source)).check(matches(isClickable()))
        onView(withId(R.id.action_open_source)).perform(click())
        onView(withText("Open source licenses")).check(matches(isDisplayed()))
    }

    @Test
    fun loadDataMovie() {
        onView(withId(R.id.movie_list_rv)).check(matches(isDisplayed()))
        // scroll to max
        onView(withId(R.id.movie_list_rv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun loadDataTvShow() {
        onView(withText("TV SHOW")).check(matches(isDisplayed()))
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.tvshow_list_rv)).check(matches(isDisplayed()))
        // scroll to max
        onView(withId(R.id.tvshow_list_rv)).apply {
            check(matches(isDisplayed()))
            perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    19
                )
            )
        }
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.movie_list_rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        checkDetail(dummyMovie)
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).check(matches(isDisplayed()))
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.tvshow_list_rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        checkDetail(dummyTvShow)
    }

    @Test
    fun emptyDataMovie() {
        assertEquals(emptyData.size, 0)
        onView(withId(R.id.movie_empty_tv)).perform(setVisibility(true))
        onView(withId(R.id.movie_empty_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_list_rv)).perform(setVisibility(false))
    }

    @Test
    fun emptyDataTvShow() {
        onView(withText("TV SHOW")).check(matches(isDisplayed()))
        onView(withText("TV SHOW")).perform(click())
        assertEquals(emptyData.size, 0)
        onView(withId(R.id.tvshow_empty_tv)).perform(setVisibility(true))
        onView(withId(R.id.tvshow_empty_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshow_list_rv)).perform(setVisibility(false))
    }

    private fun checkDetail(catalogue: CatalogueDetailEntity) {
        catalogue.apply {

            onView(withId(R.id.detail_title_tv)).apply {
                check(matches(isDisplayed()))
                check(matches(withText(name)))
            }
            onView(withId(R.id.detail_score_tv)).apply {
                check(matches(isDisplayed()))
                check(matches(withText("${voteAverage}%")))
            }
            onView(withId(R.id.detail_genre_tv)).apply {
                check(matches(isDisplayed()))
                check(matches(withText(genres)))
            }
            onView(withId(R.id.detail_year_tv)).apply {
                check(matches(isDisplayed()))
                check(matches(withText(toReadableDate(releaseDate.toString()))))
            }
            onView(withId(R.id.detail_overview_tv)).apply {
                check(matches(isDisplayed()))
                check(matches(withText(overview)))
            }
            onView(withId(R.id.detail_thumbnail_iv)).apply {
                check(matches(isDisplayed()))
                check(matches(withTagValue(CoreMatchers.equalTo(posterPath))))
            }
        }
    }

    private fun setVisibility(value: Boolean): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(View::class.java)
            }

            override fun perform(uiController: UiController?, view: View) {
                view.visibility = if (value) View.VISIBLE else View.GONE
            }

            override fun getDescription(): String {
                return "Show / Hide View"
            }
        }
    }
}