package com.moovel.android.coding.challenge.utils

import android.support.test.espresso.IdlingResource

/**
 * Created by viktor.chukholskiy
 * 14/08/18.
 */
private val RESOURCE = "IDLING_DATA"

private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

fun idlingIncrement() {
	mCountingIdlingResource.increment()
}

fun idlingDecrement() {
	mCountingIdlingResource.decrement()
}

fun getIdlingResource(): IdlingResource {
	return mCountingIdlingResource
}