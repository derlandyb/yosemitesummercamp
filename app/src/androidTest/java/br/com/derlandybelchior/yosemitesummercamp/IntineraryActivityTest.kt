package br.com.derlandybelchior.yosemitesummercamp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import br.com.derlandybelchior.yosemitesummercamp.adapter.MenuViewHolder
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class IntineraryActivityTest {

    @get:Rule
    val intineraryActivityTestRule = ActivityTestRule<IntineraryActivity>(IntineraryActivity::class.java)

    private lateinit var recyclerView: RecyclerView

    @Before fun setup() {
        recyclerView = intineraryActivityTestRule.activity.findViewById(R.id.rv_menu_items)
    }

    @Test fun test_menu_items_is_visible_on_app_lauch() {
        onView(withId(R.id.rv_menu_items)).check(matches(isDisplayed()))
    }

    @Test fun test_click_on_item_menu_then_change_status() {
        onView(withId(R.id.rv_menu_items)).perform(RecyclerViewActions.actionOnItemAtPosition<MenuViewHolder>(0, click()))

        onView(withRecyclerView(R.id.rv_menu_items)
            .atPositionOnView(0, R.id.iv_selected))
            .check(matches(isDisplayed()))
    }

    @Test fun test_click_on_item_menu_then_button_continue_is_enabled() {
        onView(withId(R.id.bt_continue)).check(matches(not(isEnabled())))
        onView(withId(R.id.bt_continue)).check(matches(withBackground(R.color.colorMediumGrey)))
        onView(withId(R.id.rv_menu_items)).perform(RecyclerViewActions.actionOnItemAtPosition<MenuViewHolder>(0, click()))

        onView(withId(R.id.bt_continue)).check(matches(isEnabled()))
        onView(withId(R.id.bt_continue)).check(matches(withBackground(R.color.colorAccent)))
    }

    private fun withRecyclerView(recyclerViewId: Int) : RecyclerViewMatcher{
        return RecyclerViewMatcher(recyclerViewId)
    }
}