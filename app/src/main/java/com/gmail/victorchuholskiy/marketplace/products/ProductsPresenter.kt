package com.gmail.victorchuholskiy.marketplace.products

import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.utils.ProductsNameComparator
import com.moovel.android.coding.challenge.utils.getIdlingResource
import com.moovel.android.coding.challenge.utils.idlingDecrement
import com.moovel.android.coding.challenge.utils.idlingIncrement
import java.util.*
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 */
@ActivityScoped
class ProductsPresenter @Inject constructor(private val getProductUseCase: GetProductsDBUseCase) : ProductsContract.Presenter {

	private var view: ProductsContract.View? = null

	private var dataLoaded = false

	override fun loadProducts() {
		idlingIncrement() // App is busy until further notice
		view!!.showProgress()
		getProductUseCase.execute()
				.subscribe(
						{
							dataLoaded = true
							idlingDecrement()
							Collections.sort(it, ProductsNameComparator())
							view!!.showProduct(it)
							view!!.hideProgress()
						},
						{
							idlingDecrement()
							view!!.showError(it)
							view!!.hideProgress()
						})
	}


	override fun takeView(view: ProductsContract.View) {
		this.view = view
		if (!dataLoaded) {
			loadProducts()
		}
	}

	override fun dropView() {
		view = null
	}
}