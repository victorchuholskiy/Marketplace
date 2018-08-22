package com.gmail.victorchuholskiy.marketplace.products

import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClientImpl
import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.di.FragmentScoped
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsDBUseCaseImpl
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCaseImpl
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsDBUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 *
 * This is a Dagger module. We use this to pass in the View dependency to the
 * [ProductsPresenter].
 */
@Module
abstract class ProductsModule {
	@FragmentScoped
	@ContributesAndroidInjector
	abstract fun usersFragment(): ProductsFragment

	@ActivityScoped
	@Binds
	abstract fun usersPresenter(presenter: ProductsPresenter): ProductsContract.Presenter

	@Module
	companion object {
		@JvmStatic
		@Provides
		@ActivityScoped
		fun provideGetProductsUseCase(context: Context): GetProductsDBUseCase {
			return GetProductsDBUseCaseImpl(context)
		}

		@JvmStatic
		@Provides
		@ActivityScoped
		fun provideLoadProductsUseCase(): LoadProductsUseCase {
			return LoadProductsUseCaseImpl(RestClientImpl)
		}

		@JvmStatic
		@Provides
		@ActivityScoped
		fun provideSaveProductsUseCase(context: Context): SaveProductsDBUseCase {
			return SaveProductsDBUseCaseImpl(context)
		}
	}
}