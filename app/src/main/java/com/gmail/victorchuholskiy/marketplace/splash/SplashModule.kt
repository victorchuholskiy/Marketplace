package com.gmail.victorchuholskiy.marketplace.splash


import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClientImpl
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsUseCaseImpl
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
	abstract fun splashPresenter(presenter: SplashPresenter): SplashContract.Presenter

	@Module
	companion object {
		@JvmStatic
		@Provides
		@ActivityScoped
		fun provideLoadProductsUseCase(): SaveProductsUseCase {
			return SaveProductsUseCaseImpl(RestClientImpl)
		}
	}
}