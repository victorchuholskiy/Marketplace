package com.gmail.victorchuholskiy.marketplace.splash

import android.content.Intent
import android.widget.Toast
import com.gmail.victorchuholskiy.marketplace.products.ProductsActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 *
 * Simple splash activity for solving the problem of blank white page at the start of the app
 * and loading list of products during the first run
 */
class SplashActivity : DaggerAppCompatActivity(), SplashContract.View {

	@Inject
	lateinit var presenter: SplashContract.Presenter

	override fun onResume() {
		super.onResume()
		presenter.takeView(this)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.dropView()
	}

	override fun navigateToNext() {
		startActivity(Intent(this@SplashActivity, ProductsActivity::class.java))
		finish()
	}

	override fun showError(msg: String) {
		Toast.makeText(this, msg , Toast.LENGTH_SHORT).show()
		finish()
	}
}