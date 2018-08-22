package com.gmail.victorchuholskiy.marketplace.useCases.getCountOfProducts

import com.gmail.victorchuholskiy.marketplace.useCases.UseCase
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface GetCountOfProductsDBUseCase: UseCase<Int> {
	override fun execute(): Observable<Int>
}