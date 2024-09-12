package com.clovertech.autolib

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.clovertech.autolib.views.activities.HomeActivity
import com.clovertech.autolib.adapters.MyViewHolder
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class TaskTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<HomeActivity> =
        ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.clovertech.autolib", appContext.packageName)
    }

    @Test(expected = PerformException::class)
    fun taskListClick() {
        onView(withId(R.id.pagerTasksHome))
            .perform(actionOnItemAtPosition<MyViewHolder>(1, click()))
        //Vérification
        onView(withId(R.id.Titre)).check(matches(withText("This is the task Title")))
        onView(withId(R.id.notifications_recycler))
            .perform(actionOnItemAtPosition<MyViewHolder>(1, click()))

    }

    @Test(expected = PerformException::class)
    fun test_display_task_detail() {
        onView(withId(R.id.pagerTasksHome))
            .perform(actionOnItemAtPosition<MyViewHolder>(1, click()))
        onView(withId(R.id.details)).perform(click())
        onView(withId(R.id.task_detail_title)).check(matches(withText("This is the task Title")))
        pressBack()
        onView(withId(R.id.pagerTasksHome)).check(matches(isDisplayed()))
    }


}