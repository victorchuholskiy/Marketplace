package com.gmail.victorchuholskiy.marketplace.splash

import com.gmail.victorchuholskiy.marketplace.BasePresenter
import com.gmail.victorchuholskiy.marketplace.BaseView

/**
 * Created by viktor.chukholskiy
 * 25/07/18.

 * This specifies the contract between the view and the presenter.
 */
interface SplashContract {

	interface View : BaseView<Presenter> {
		fun navigateToNext()

		fun showError(msg: String)
	}

	interface Presenter : BasePresenter<View> {
		fun loadProducts()
	}
}