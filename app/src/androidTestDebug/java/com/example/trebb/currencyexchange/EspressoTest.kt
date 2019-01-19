package com.example.trebb.currencyexchange

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

@LargeTest
 class CurrencyServiceEspressoTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun isCalculateButtonClickable() {
        onView(withId(R.id.btn_search)).check(matches(isClickable()))
    }

    @Test
    fun isCalculateButtonEnabled() {
        onView(withId(R.id.btn_search)).check(matches(isEnabled()))
    }

    @Test
    fun isCalculateButtonIsDisplayed() {
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
    }


    @Test
    fun isDropdownClickable(){
        onView(withId(R.id.spinner)).check(matches(isClickable()))
    }

    @Test
    fun isCalculatorActivityPresent(){
        onView(withId(R.id.btn_search)).perform(click())
        onView(withId(R.id.textView3)).check(matches(isDisplayed()))
    }
}