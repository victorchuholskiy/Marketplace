package com.gmail.victorchuholskiy.marketplace.splash

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.R
import com.gmail.victorchuholskiy.marketplace.useCases.getCountOfProducts.GetCountOfProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsUseCase
import io.reactivex.Observable

import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class SplashPresenter @Inject constructor(private val getCountOfProductsUseCase: GetCountOfProductsUseCase,
										  private val loadProductsUseCase : SaveProductsUseCase,
										  private val context: Context?) : SplashContract.Presenter {

	private var view: SplashContract.View? = null

	override fun loadProducts() {
		getCountOfProductsUseCase.execute()
				.flatMap {
					if (it == 0) {
						loadProductsUseCase.execute()
					} else {
						Observable.just(true)
					}
				}
				.subscribe({
					if (it) view!!.navigateToNext() else view!!.showError(context!!.getString(R.string.def_error))
				}, {
					view!!.showError(it.message!!)
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