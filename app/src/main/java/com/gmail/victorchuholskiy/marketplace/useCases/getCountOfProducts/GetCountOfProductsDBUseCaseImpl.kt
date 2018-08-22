package com.gmail.victorchuholskiy.marketplace.useCases.getCountOfProducts

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class GetCountOfProductsDBUseCaseImpl @Inject constructor(private val context: Context): GetCountOfProductsDBUseCase {

	override fun execute(): Observable<Int> {
		return ProductsDataBase.getInstance(context)!!
				.productsDao()
				.getNumberOfRows()
				.toObservable()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
	}
}
