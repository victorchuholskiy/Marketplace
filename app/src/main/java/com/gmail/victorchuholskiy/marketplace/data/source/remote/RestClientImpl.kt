package com.gmail.victorchuholskiy.marketplace.data.source.remote

import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse

import com.google.gson.Gson
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
object RestClientImpl : RestClient {

	private val mApiService: ApiService = createService(
			ApiService::class.java,
			baseUrl,
			Gson())

	override fun getProducts(): Observable<ProductsResponse> {
		return mApiService.getProducts()
	}

}
