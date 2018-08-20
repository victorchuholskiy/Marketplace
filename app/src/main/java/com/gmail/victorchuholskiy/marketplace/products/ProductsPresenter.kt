package com.gmail.victorchuholskiy.marketplace.products

import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsUseCase
import com.moovel.android.coding.challenge.di.ActivityScoped
import com.moovel.android.coding.challenge.useCases.loadUsers.LoadUsersUseCase
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
		idlingIncrement() // App is busy until further notice
		view!!.showProgress()
		getProductUseCase.execute()
				.subscribe(
						{
							idlingDecrement()
							/*if (getIdlingResource().isIdleNow) {
								idlingDecrement()
							}*/
							currentPage = page
							view!!.showUsers(it, clear)
							view!!.hideProgress()
						},
						{
							idlingDecrement()
							/*if (getIdlingResource().isIdleNow) {
								idlingDecrement()
							}*/
							view!!.showError(it)
							view!!.hideProgress()
						})
	}

	override fun loadUsersPage(page: Int, clear: Boolean) {
		idlingIncrement() // App is busy until further notice
		view!!.showProgress()
		getProductUseCase.execute(LoadUsersUseCase.Params.pageParams(page))
				.subscribe(
						{
							idlingDecrement()
							/*if (getIdlingResource().isIdleNow) {
								idlingDecrement()
							}*/
							currentPage = page
							view!!.showUsers(it, clear)
							view!!.hideProgress()
						},
						{
							idlingDecrement()
							/*if (getIdlingResource().isIdleNow) {
								idlingDecrement()
							}*/
							view!!.showError(it)
							view!!.hideProgress()
						})
	}

	override fun takeView(view: ProductsContract.View) {
		this.view = view
		if (currentPage == 0) {
			loadUsersPage(1, true)
		}
	}

	override fun dropView() {
		view = null
	}
}