package com.gmail.victorchuholskiy.marketplace

import android.app.Application
import dagger.android.DaggerApplication
import com.gmail.victorchuholskiy.marketplace.data.source.local.ProductsDataBase
import com.gmail.victorchuholskiy.marketplace.di.DaggerAppComponent
import dagger.android.AndroidInjector


/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 *
 * I create a custom [Application] class that extends  [DaggerApplication].
 * Then override applicationInjector() which tells Dagger how to make our @Singleton Component
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