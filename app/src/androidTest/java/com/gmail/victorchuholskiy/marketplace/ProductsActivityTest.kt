package com.gmail.victorchuholskiy.marketplace

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.gmail.victorchuholskiy.marketplace.products.ProductsActivity
import com.gmail.victorchuholskiy.marketplace.utils.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.gmail.victorchuholskiy.marketplace.utils.getIdlingResource
import org.hamcrest.Matchers.greaterThan
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by viktor.chukholskiy
 * 22/08/18.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductsActivityTest {
	@Rule @JvmField val activityRule = ActivityTestRule<ProductsActivity>(ProductsActivity::class.java)

	/**
	 * Prepare your test fixture for this test. In this case we register an IdlingResources with
	 * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
	 * idle state. This helps Espresso to synchronize your test actions, which makes tests
	 * significantly more reliable.
	 */
	@Before
	fun registerIdlingResource() {
		IdlingRegistry.getInstance().register(getIdlingResource())
	}

	/**
	 * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
	 */
	@After
	fun unregisterIdlingResource() {
		IdlingRegistry.getInstance().unregister(getIdlingResource())
	}

	@Test
	fun recyclerViewTest() {
		Espresso.onView(ViewMatchers.withId(R.id.rv_products)).check(withItemCount(greaterThan(0)))

		Espresso.onView(ViewMatchers.withId(R.id.rv_products))
				.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

		Espresso.onView(ViewMatchers.withId(R.id.zv_zoom)).check(matches(isDisplayed()))
	}
}