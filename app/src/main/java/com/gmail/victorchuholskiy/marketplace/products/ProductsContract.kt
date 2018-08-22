package com.gmail.victorchuholskiy.marketplace.products

import com.gmail.victorchuholskiy.marketplace.BasePresenter
import com.gmail.victorchuholskiy.marketplace.BaseView
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 *
 * This specifies the contract between the view and the presenter.
 */
interface ProductsContract {

	interface View : BaseView<Presenter> {
		fun showProduct(products: List<Product>)

		fun showProgress()

		fun hideProgress()

		fun showError(exception: Throwable?)
	}

	interface Presenter : BasePresenter<View> {
		fun loadProducts()

		fun updateProductsFromServer()
	}
}