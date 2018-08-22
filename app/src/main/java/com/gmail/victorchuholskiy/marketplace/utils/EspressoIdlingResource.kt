package com.gmail.victorchuholskiy.marketplace.utils

import android.support.test.espresso.IdlingResource

/**
 * Created by viktor.chukholskiy
 * 14/08/18.
 */
private val RESOURCE = "IDLING_DATA"

private val mCountingIdlingResource = CountingIdlingResource(RESOURCE)

fun idlingIncrement() {
	mCountingIdlingResource.increment()
}

fun idlingDecrement() {
	mCountingIdlingResource.decrement()
}

fun getIdlingResource(): IdlingResource {
	return mCountingIdlingResource
}