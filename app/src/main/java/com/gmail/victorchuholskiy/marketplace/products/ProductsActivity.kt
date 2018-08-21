package com.gmail.victorchuholskiy.marketplace.products

import android.os.Bundle
import com.gmail.victorchuholskiy.marketplace.R
import com.moovel.android.coding.challenge.utils.replaceFragmentInActivity
import com.moovel.android.coding.challenge.utils.setupActionBar
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class ProductsActivity : DaggerAppCompatActivity() {
	@Inject
	lateinit var productsFragmentProvider: Lazy<ProductsFragment>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_products)

		// Set up the toolbar.
		setupActionBar(R.id.toolbar) {}

		supportFragmentManager.findFragmentById(R.id.contentFrame)
				as ProductsFragment? ?: productsFragmentProvider.get().also {
			replaceFragmentInActivity(it, R.id.contentFrame)
		}
	}
}
