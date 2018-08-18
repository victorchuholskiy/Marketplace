package com.gmail.victorchuholskiy.marketplace.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class ProductsResponse {

	@SerializedName("products")
	val products: List<Product>? = null

	inner class Product {

		@SerializedName("identifier")
		val id: Int = 0

		@SerializedName("name")
		val name: String? = null

		@SerializedName("brand")
		val brand: String? = null

		@SerializedName("original_price")
		val originalPrice: Double? = null

		@SerializedName("current_price")
		val currentPrice: Double? = null

		@SerializedName("currency")
		val currency: String? = null

		@SerializedName("image")
		val image: Image? = null

		inner class Image {

			@SerializedName("id")
			val id: Int = 0

			@SerializedName("url")
			val url: String? = null
		}
	}
}