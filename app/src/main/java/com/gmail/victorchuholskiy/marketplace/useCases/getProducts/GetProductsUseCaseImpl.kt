package com.gmail.victorchuholskiy.marketplace.useCases.getProducts

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class GetProductsUseCaseImpl @Inject constructor(private val context: Context?): GetProductsUseCase {

	override fun execute(params: Nothing?): Observable<List<Product>> {
		return ProductsDataBase.getInstance(context!!)!!.productsDao().getAll().toObservable()
	}
}