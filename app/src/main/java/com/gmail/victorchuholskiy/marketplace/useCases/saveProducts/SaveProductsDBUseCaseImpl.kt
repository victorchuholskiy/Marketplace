package com.gmail.victorchuholskiy.marketplace.useCases.saveProducts

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class SaveProductsDBUseCaseImpl @Inject constructor(private val context: Context) : SaveProductsDBUseCase {

	private var response: ProductsResponse? = null

	override fun execute(): Observable<Boolean> {
		if (response == null) {
			return Observable.error(IllegalStateException())
		}
		return Observable.just(response)
				.map {
					if (it.products != null) {
						val db = ProductsDataBase.getInstance(context)
						val list = ArrayList<Product>()
						val products = it.products!!
						for (productResponse in products) {
							list.add(
									Product(
											productResponse.id,
											productResponse.name!!,
											productResponse.brand!!,
											productResponse.originalPrice!!,
											productResponse.currentPrice!!,
											productResponse.currency!!,
											productResponse.image!!.url!!
									))
						}
						db!!.productsDao().deleteProducts()
						db.productsDao().insertAll(list)
						true
					} else {
						false
					}
				}
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
	}

	override fun setResponse(response: ProductsResponse) {
		this.response = response
	}
}