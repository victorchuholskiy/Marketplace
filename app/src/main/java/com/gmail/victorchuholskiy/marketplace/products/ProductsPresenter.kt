package com.gmail.victorchuholskiy.marketplace.products

import android.util.Log
import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsUseCase
import com.moovel.android.coding.challenge.utils.getIdlingResource
import com.moovel.android.coding.challenge.utils.idlingDecrement
import com.moovel.android.coding.challenge.utils.idlingIncrement
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 */
@ActivityScoped
class ProductsPresenter @Inject constructor(private val getProductUseCase: GetProductsUseCase) : ProductsContract.Presenter {

	private var view: ProductsContract.View? = null

	override fun loadProducts() {
		Log.d("AAA", "loadProducts")
		idlingIncrement() // App is busy until further notice
		view!!.showProgress()
		getProductUseCase.execute()
				.subscribe(
						{
							Log.d("AAA", "count ProductsPresenter" + it.size)
							if (getIdlingResource().isIdleNow) {
								idlingDecrement()
							}
							view!!.showProduct(it)
							view!!.hideProgress()
						},
						{
							idlingDecrement()
							if (getIdlingResource().isIdleNow) {
								idlingDecrement()
							}
							view!!.showError(it)
							view!!.hideProgress()
						})
	}


	override fun takeView(view: ProductsContract.View) {
		this.view = view
		loadProducts()
	}

	override fun dropView() {
		view = null
	}
}