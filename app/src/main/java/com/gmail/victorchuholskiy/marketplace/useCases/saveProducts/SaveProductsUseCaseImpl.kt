package com.gmail.victorchuholskiy.marketplace.useCases.saveProducts

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class SaveProductsUseCaseImpl @Inject constructor(private val restClient: RestClient,
												  private val context: Context) : SaveProductsUseCase {

	override fun execute(): Observable<Boolean> {
		return restClient
				.getProducts()
				.map {
					if (it.products != null) {
						val db = ProductsDataBase.getInstance(context)
						val list = ArrayList<Product>()
						for (productResponse in it.products) {
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
						db!!.productsDao().insertAll(list)
						true
					} else {
						false
					}
				}
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
	}
}