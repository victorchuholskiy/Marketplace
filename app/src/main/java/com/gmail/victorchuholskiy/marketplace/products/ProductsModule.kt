package com.gmail.victorchuholskiy.marketplace.products

import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.di.FragmentScoped
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsUseCaseImpl
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
		fun provideLoadUsersUseCase(): GetProductsUseCase {
			return GetProductsUseCaseImpl()
		}
	}
}