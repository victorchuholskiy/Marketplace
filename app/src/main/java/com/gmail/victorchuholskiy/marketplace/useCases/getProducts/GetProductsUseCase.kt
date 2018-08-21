package com.gmail.victorchuholskiy.marketplace.useCases.getProducts

import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.gmail.victorchuholskiy.marketplace.useCases.UseCase
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface GetProductsUseCase: UseCase<List<Product>> {
	override fun execute(): Observable<List<Product>>
}