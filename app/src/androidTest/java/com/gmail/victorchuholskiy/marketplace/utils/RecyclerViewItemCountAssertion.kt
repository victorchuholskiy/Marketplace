package com.gmail.victorchuholskiy.marketplace.utils


import android.support.v7.widget.RecyclerView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`


/**
 * Created by viktor.chukholskiy
 * 22/08/18.
 */
class RecyclerViewItemCountAssertion private constructor(private val matcher: Matcher<Int>) : ViewAssertion {

	override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
		if (noViewFoundException != null) {
			throw noViewFoundException
		}
		if (view !is RecyclerView) {
			throw IllegalStateException("The asserted view is not RecyclerView")
		}
		if (view.adapter == null) {
			throw IllegalStateException("No adapter is assigned to RecyclerView")
		}
		val adapter = view.adapter
		assertThat(adapter!!.itemCount, matcher)
	}

	companion object {
		fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
			return withItemCount(`is`(expectedCount))
		}

		fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
			return RecyclerViewItemCountAssertion(matcher)
		}
	}
}