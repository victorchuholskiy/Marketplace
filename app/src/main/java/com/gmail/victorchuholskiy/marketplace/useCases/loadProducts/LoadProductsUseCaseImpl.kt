package com.gmail.victorchuholskiy.marketplace.useCases.loadProducts

import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClient
import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class LoadProductsUseCaseImpl @Inject constructor(private val restClient: RestClient) : LoadProductsUseCase {

	override fun execute(): Observable<ProductsResponse> {
		return restClient
				.getProducts()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
	}
}