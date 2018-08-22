package com.gmail.victorchuholskiy.marketplace.products

import android.os.Bundle
import android.view.MenuItem
import com.gmail.victorchuholskiy.marketplace.R
import com.gmail.victorchuholskiy.marketplace.utils.ARG_URL
import com.gmail.victorchuholskiy.marketplace.utils.DETAILS_FRAGMENT_TAG
import com.gmail.victorchuholskiy.marketplace.utils.PRODUCT_FRAGMENT_TAG
import com.moovel.android.coding.challenge.utils.replaceFragmentAddToBackStack
import com.moovel.android.coding.challenge.utils.replaceFragmentInActivity
import com.moovel.android.coding.challenge.utils.setupActionBar
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_products.*
import javax.inject.Inject


class ProductsActivity : DaggerAppCompatActivity(), ProductDetailsListener {
	@Inject
	lateinit var productsFragmentProvider: Lazy<ProductsFragment>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_products)

		// Set up the toolbar.
		setupActionBar(R.id.toolbar) {
			title = getString(R.string.products)
		}

		supportFragmentManager.findFragmentById(R.id.contentFrame)
				as ProductsFragment? ?: productsFragmentProvider.get().also {
			replaceFragmentInActivity(it, R.id.contentFrame)
		}
	}

	override fun showDetails(url: String, name: String) {
		initDetailsToolbar(name)
		val detailsFragment = DetailsFragment()
		val bundle = Bundle()
		bundle.putString(ARG_URL, url)
		detailsFragment.arguments = bundle
		replaceFragmentAddToBackStack(detailsFragment, R.id.contentFrame, DETAILS_FRAGMENT_TAG)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == android.R.id.home) {
			onBackPressed()
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onBackPressed() {
		super.onBackPressed()
		initProductsToolbar()
	}

	private fun initDetailsToolbar(name: String) {
		apl_appBar.setExpanded(true, true)
		supportActionBar!!.apply {
			setDisplayHomeAsUpEnabled(true)
			setDisplayShowHomeEnabled(true)
			title = name
		}
	}

	private fun initProductsToolbar() {
		supportActionBar!!.apply {
			setDisplayHomeAsUpEnabled(false)
			setDisplayShowHomeEnabled(false)
			title = getString(R.string.products)
		}
	}
}
