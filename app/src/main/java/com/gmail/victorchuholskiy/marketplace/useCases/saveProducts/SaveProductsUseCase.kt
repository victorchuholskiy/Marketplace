package com.gmail.victorchuholskiy.marketplace.useCases.saveProducts

import com.gmail.victorchuholskiy.marketplace.useCases.UseCase
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface SaveProductsUseCase: UseCase<Boolean> {
	override fun execute(): Observable<Boolean>
}