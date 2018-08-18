package com.gmail.victorchuholskiy.marketplace

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase


/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
class MarketplaceApp: DaggerApplication() {
	override fun onCreate() {
		super.onCreate()
		ProductsDataBase.getInstance(this)
	}

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
		return DaggerAppComponent.builder().application(this).build()
	}
}