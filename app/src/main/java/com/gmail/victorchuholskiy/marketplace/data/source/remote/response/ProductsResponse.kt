package com.gmail.victorchuholskiy.marketplace.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class ProductsResponse() {

	constructor(products: List<Product>) : this() {
		this.products = products
	}

	@SerializedName("products")
	var products: List<Product>? = null

	class Product {

		@SerializedName("identifier")
		var id: Int = 0

		@SerializedName("name")
		var name: String? = null

		@SerializedName("brand")
		var brand: String? = null

		@SerializedName("original_price")
		var originalPrice: Double? = null

		@SerializedName("current_price")
		var currentPrice: Double? = null

		@SerializedName("currency")
		var currency: String? = null

		@SerializedName("image")
		var image: Image? = null

		class Image {

			@SerializedName("id")
			var id: Int = 0

			@SerializedName("url")
			var url: String? = null
		}
	}
}