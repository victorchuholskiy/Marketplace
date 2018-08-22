package com.gmail.victorchuholskiy.marketplace.useCases.loadProducts

import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import com.gmail.victorchuholskiy.marketplace.useCases.UseCase
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface LoadProductsUseCase: UseCase<ProductsResponse> {
	override fun execute(): Observable<ProductsResponse>
}