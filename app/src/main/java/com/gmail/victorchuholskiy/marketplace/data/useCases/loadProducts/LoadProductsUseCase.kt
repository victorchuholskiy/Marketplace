package com.gmail.victorchuholskiy.marketplace.data.useCases.loadProducts

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.useCases.UseCase
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface LoadProductsUseCase: UseCase<LoadProductsUseCase.Params, Boolean> {
	override fun execute(params: LoadProductsUseCase.Params): Observable<Boolean>

	class Params private constructor(val context: Context) {
		companion object {

			fun context(context: Context): Params {
				return Params(context)
			}
		}
	}
}