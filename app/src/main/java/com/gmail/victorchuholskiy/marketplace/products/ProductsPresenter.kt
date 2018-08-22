package com.gmail.victorchuholskiy.marketplace.products

import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.utils.ProductsNameComparator
import com.gmail.victorchuholskiy.marketplace.utils.getIdlingResource
import com.gmail.victorchuholskiy.marketplace.utils.idlingDecrement
import com.gmail.victorchuholskiy.marketplace.utils.idlingIncrement
import java.util.*
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 */
@ActivityScoped
class ProductsPresenter @Inject constructor(private val getProductUseCase: GetProductsDBUseCase,
											private val loadProductsUseCase : LoadProductsUseCase,
											private val saveProductsDBUseCase: SaveProductsDBUseCase) : ProductsContract.Presenter {

	private var view: ProductsContract.View? = null

	private var dataLoaded = false

	override fun loadProducts() {
		if (!dataLoaded) {
			view!!.showProgress()
			loadProductsFromDB()
		}
	}

	override fun updateProductsFromServer() {
		view!!.showProgress()
		loadProductsUseCase.execute()
				.flatMap {response ->
					saveProductsDBUseCase.setResponse(response)
					saveProductsDBUseCase.execute()
				}
				.subscribe(
						{
							loadProductsFromDB()
						},
						{
							if (!getIdlingResource().isIdleNow) {
								idlingDecrement()
							}
							view!!.showError(it)
							view!!.hideProgress()
						})
	}

	override fun takeView(view: ProductsContract.View) {
		this.view = view
	}

	override fun dropView() {
		view = null
	}

	private fun loadProductsFromDB() {
		idlingIncrement() // App is busy until further notice
		getProductUseCase.execute()
				.subscribe(
						{
							dataLoaded = true
							if (!getIdlingResource().isIdleNow) {
								idlingDecrement()
							}
							Collections.sort(it, ProductsNameComparator())
							view!!.showProduct(it)
							view!!.hideProgress()
						},
						{
							if (!getIdlingResource().isIdleNow) {
								idlingDecrement()
							}
							view!!.showError(it)
							view!!.hideProgress()
						})
	}
}