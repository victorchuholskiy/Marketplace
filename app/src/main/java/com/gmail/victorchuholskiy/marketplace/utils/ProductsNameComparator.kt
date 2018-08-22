package com.gmail.victorchuholskiy.marketplace.utils

import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product

/**
 * Created by viktor.chukholskiy
 * 21/08/18.
 */
class ProductsNameComparator: Comparator<Product> {

	override fun compare(p0: Product?, p1: Product?): Int {
		if (p0!!.name == p1!!.name) {
			return p0.id.compareTo(p1.id)
		}
		return p0.name.compareTo(p1.name)
	}
}