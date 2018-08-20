package com.gmail.victorchuholskiy.marketplace.useCases.saveProducts

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.useCases.UseCase
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface SaveProductsUseCase: UseCase<SaveProductsUseCase.Params, Boolean> {
	override fun execute(params: Params): Observable<Boolean>

	class Params private constructor(val context: Context) {
		companion object {

			fun context(context: Context): Params {
				return Params(context)
			}
		}
	}
}