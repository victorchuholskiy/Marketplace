package com.gmail.victorchuholskiy.marketplace.data.source.remote

import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 *
 * Main REST interface that describes necessary API methods and contains base URL
 */
interface RestClient {

	/**
	 * Base URL of Api.
	 * The link is unchanged, so it's logical to store it directly in the interface that defines the required set of methods.
	 */
	val baseUrl : String
		get() = "https://private-91dd6-iosassessment.apiary-mock.com/"

	fun getProducts() : Observable<ProductsResponse>
}