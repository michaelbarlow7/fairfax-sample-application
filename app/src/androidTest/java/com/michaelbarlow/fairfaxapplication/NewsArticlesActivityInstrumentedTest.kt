package com.michaelbarlow.fairfaxapplication

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.android.AndroidLogger
import io.appflate.restmock.logging.RESTMockLogger
import io.appflate.restmock.utils.RequestMatchers.isGET
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NewsArticlesActivityInstrumentedTest {

    companion object {
        const val INITIAL_DELAY = 500L
    }

    @get:Rule
    var activityRule: ActivityTestRule<NewsArticlesActivity> = ActivityTestRule(NewsArticlesActivity::class.java, false, false)

    @Before
    fun setup() {
        // Register idling resource
        RESTMockServer.enableLogging(AndroidLogger())
        RESTMockServer.reset()

    }


    @Test
    fun loadingAndSuccessResponse() {
        Intents.init()
        // Success response
        RESTMockServer.whenRequested(isGET())
            .delay(TimeUnit.MILLISECONDS, INITIAL_DELAY)
            .thenReturnFile(200, "success.json")
        activityRule.launchActivity(null)


        // Check initial loading view
        onView(withId(R.id.statusText))
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("Loading"))))

        Thread.sleep(INITIAL_DELAY)

        onView(withId(R.id.statusText))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.newsArticleRecyclerView))
            .check(matches(isDisplayed()))

        Intents.release()
    }

    @Test
    fun errorResponse() {
        Intents.init()
        // Error response
        RESTMockServer.whenRequested(isGET())
            .thenReturnEmpty(404)

        activityRule.launchActivity(null)
        onView(withId(R.id.statusText))
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("Error"))))

        Intents.release()
    }

    @Test
    fun emptyResponse() {
        Intents.init()

        // Empty response
        RESTMockServer.whenRequested(isGET())
            .thenReturnFile(200, "empty.json")

        activityRule.launchActivity(null)
        // Check empty view
        onView(withId(R.id.statusText))
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("No articles available!"))))

        Intents.release()
    }
}

