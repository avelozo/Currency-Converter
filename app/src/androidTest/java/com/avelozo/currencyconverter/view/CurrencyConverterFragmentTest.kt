package com.avelozo.currencyconverter.view


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.v7.widget.RecyclerView
import com.avelozo.currencyconverter.R
import org.junit.Before
import org.junit.Test
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher

@RunWith(AndroidJUnit4::class)
@LargeTest
class CurrencyConverterFragmentTest {

    @get:Rule
     var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp() {
        activityActivityTestRule.activity.supportFragmentManager.beginTransaction()
        Thread.sleep(5000)
    }


    @Test
    fun isCurrencyListBeingDisplayed(){
        onView(withId(R.id.rateRecycler)).check(matches(isDisplayed()))

    }

    @Test
    fun euroIsFirstItem(){
        onView(withId(R.id.rateRecycler)).check(
            matches(recyclerViewAtPositionOnView(0, withText("EUR"), R.id.txtCurrencyCode)))

        onView(withId(R.id.rateRecycler)).check(
            matches(recyclerViewAtPositionOnView(0, withText("Euro"), R.id.txtCurrencyName)))

    }

    @Test
    fun isToolbarTitleCorrectlyDisplayed(){
        onView(isAssignableFrom(Toolbar::class.java))
            .check(matches(withToolbarTitle(`is`("Currency Converter"))))
    }
    @Test
    fun clickOnAItem() {

        onView(withId(R.id.rateRecycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rateRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )


    }


    private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> =
        object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {

            override fun matchesSafely(toolbar: Toolbar): Boolean =
                textMatcher.matches(toolbar.title)

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }


    private fun recyclerViewAtPositionOnView(position: Int, itemMatcher: Matcher<View>,  targetViewId: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            public override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                val targetView = viewHolder.itemView.findViewById<View>(targetViewId) as TextView
                return itemMatcher.matches(targetView)
            }
        }
    }


}

