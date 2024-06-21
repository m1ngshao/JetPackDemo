package com.example.jetpackdemo


import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.jetpackdemo.adapter.DashboardListAdapter
import com.example.jetpackdemo.ui.activity.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasEntry
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.core.Is.`is`

import org.junit.Test
import org.junit.Rule
import java.util.EnumSet.allOf
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun dashboard_click_startActivity(){
        val latch = CountDownLatch(1)
        val backgroundThread =  Thread {
            onView(withId(R.id.navigation_dashboard)).perform(click())
            var isLoaded = true
            while(isLoaded){
                try {
                    onView(withId(R.id.dash_recycler_view)).perform(actionOnItemAtPosition<DashboardListAdapter.MyViewHolder>(35, scrollTo()))
                    isLoaded = false
                }catch (e:Exception){
                    e.printStackTrace()
                    Thread.sleep(1000)
                }
            }
            onView(withId(R.id.dash_recycler_view)).perform(scrollToPosition<DashboardListAdapter.MyViewHolder>(50),
                actionOnItemAtPosition<DashboardListAdapter.MyViewHolder>(50, click()))

            onView(withId(R.id.detail_toolbar_back)).perform(click())
            latch.countDown()
        }
        backgroundThread.start()
        latch.await()

    }
}