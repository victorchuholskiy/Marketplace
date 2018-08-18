package com.gmail.victorchuholskiy.marketplace.data.useCases.loadProducts

import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class LoadProductsUseCaseImpl @Inject constructor(private val restClient: RestClient): LoadProductsUseCase {

	override fun execute(params: LoadProductsUseCase.Params): Observable<Boolean> {
		return restClient
				.getProducts()
				.map {
					if (it.products != null) {
						val db = ProductsDataBase.getInstance(params.context)
						for (productResponse in it.products) {
							val product = Product(
									productResponse.id,
									productResponse.name!!,
									productResponse.brand!!,
									productResponse.originalPrice!!,
									productResponse.currentPrice!!,
									productResponse.currency!!,
									productResponse.image!!.url!!
							)
							db!!.productsDao().insert(product)
						}
						true
					} else {
						false
					}
				}
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
	}
}