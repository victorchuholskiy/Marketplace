package com.gmail.victorchuholskiy.marketplace.utils

import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse

/**
 * Created by viktor.chukholskiy
 * 22/08/18.
 */
fun prepareResponseProduct(id: Int): ProductsResponse.Product {
	val image = ProductsResponse.Product.Image()
	image.id = 1
	image.url = "https://test.url"

	val product = ProductsResponse.Product()
	product.id = id
	product.name = "test_name"
	product.brand = "test_brand"
	product.currentPrice = 10.0
	product.originalPrice = 10.0
	product.currency = "USD"
	product.image = image
	return product
}

fun prepareProduct(id: Int): Product {
	return Product(
			id,
			name = "test_name",
			brand = "test_brand",
			currentPrice = 10.0,
			originalPrice = 10.0,
			currency = "USD",
			url = "https://test.url")
}