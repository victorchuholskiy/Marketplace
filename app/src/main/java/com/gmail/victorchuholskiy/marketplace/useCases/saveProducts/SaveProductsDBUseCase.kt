package com.gmail.victorchuholskiy.marketplace.useCases.saveProducts

import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import com.gmail.victorchuholskiy.marketplace.useCases.UseCase
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface SaveProductsDBUseCase: UseCase<Boolean> {

	override fun execute(): Observable<Boolean>

	fun setResponse(response: ProductsResponse)
}