package com.gmail.victorchuholskiy.marketplace.useCases.getProducts

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class GetProductsDBUseCaseImpl @Inject constructor(private val context: Context): GetProductsDBUseCase {

	override fun execute(): Observable<List<Product>> {
		return ProductsDataBase.getInstance(context)!!
				.productsDao()
				.getAll()
				.toObservable()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
	}
}
