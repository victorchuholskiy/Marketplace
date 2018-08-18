package com.gmail.victorchuholskiy.marketplace.splash


import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClientImpl
import com.gmail.victorchuholskiy.marketplace.data.useCases.loadProducts.LoadProductsUseCase
import com.gmail.victorchuholskiy.marketplace.data.useCases.loadProducts.LoadProductsUseCaseImpl
import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 *
 * This is a Dagger module. We use this to pass in the View dependency to the
 * [SplashPresenter].
 */
@Module
abstract class SplashModule {
	@ActivityScoped
	@Binds
	abstract fun userDetailsPresenter(presenter: SplashPresenter): SplashContract.Presenter

	@Module
	companion object {
	/*	@JvmStatic
		@Provides
		@ActivityScoped
		fun provideContext(activity: SplashActivity): Context {
			return activity
		}*/

		@JvmStatic
		@Provides
		@ActivityScoped
		fun provideLoadProductsUseCase(): LoadProductsUseCase {
			return LoadProductsUseCaseImpl(RestClientImpl)
		}
	}
}