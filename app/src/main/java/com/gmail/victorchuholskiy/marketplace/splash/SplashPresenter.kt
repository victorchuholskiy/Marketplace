package com.gmail.victorchuholskiy.marketplace.splash

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.R
import com.gmail.victorchuholskiy.marketplace.useCases.getCountOfProducts.GetCountOfProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsDBUseCase
import io.reactivex.Observable

import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class SplashPresenter @Inject constructor(private val getCountOfProductsUseCase: GetCountOfProductsDBUseCase,
										  private val loadProductsUseCase : LoadProductsUseCase,
										  private val saveProductsDBUseCase: SaveProductsDBUseCase,
										  private val context: Context?) : SplashContract.Presenter {

	private var view: SplashContract.View? = null

	override fun loadProducts() {
		getCountOfProductsUseCase.execute()
				.flatMap {
					if (it == 0) {
						loadProductsUseCase.execute()
								.flatMap {response ->
										saveProductsDBUseCase.setResponse(response)
										saveProductsDBUseCase.execute()
								}
					} else {
						Observable.just(true)
					}
				}
				.subscribe({
					if (it) view!!.navigateToNext() else view!!.showError(context!!.getString(R.string.def_error))
				}, {
					view!!.showError(if (it.message != null) it.message!! else context!!.getString(R.string.def_error))
				})
	}

	override fun takeView(view: SplashContract.View) {
		this.view = view
		loadProducts()
	}

	override fun dropView() {
		view = null
	}
}