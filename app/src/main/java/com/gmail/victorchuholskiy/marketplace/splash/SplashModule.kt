package com.gmail.victorchuholskiy.marketplace.splash


import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClientImpl
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsDBUseCaseImpl
import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.useCases.getCountOfProducts.GetCountOfProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.getCountOfProducts.GetCountOfProductsDBUseCaseImpl
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCaseImpl
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
		fun provideGetCountOfProductsUseCase(context: Context): GetCountOfProductsDBUseCase {
			return GetCountOfProductsDBUseCaseImpl(context)
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