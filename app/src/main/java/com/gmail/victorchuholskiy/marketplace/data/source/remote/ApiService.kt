package com.gmail.victorchuholskiy.marketplace.data.source.remote

import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
internal interface ApiService {

	@GET("products")
	fun getProducts(): Observable<ProductsResponse>
}