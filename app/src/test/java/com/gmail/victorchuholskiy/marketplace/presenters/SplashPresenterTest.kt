package com.gmail.victorchuholskiy.marketplace.presenters

import android.app.Application
import com.gmail.victorchuholskiy.marketplace.base.BaseSchedulersTest
import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import com.gmail.victorchuholskiy.marketplace.splash.SplashContract
import com.gmail.victorchuholskiy.marketplace.splash.SplashPresenter
import com.gmail.victorchuholskiy.marketplace.useCases.getCountOfProducts.GetCountOfProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.saveProducts.SaveProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.utils.capture
import com.gmail.victorchuholskiy.marketplace.utils.prepareResponseProduct
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by viktor.chukholskiy
 * 12/08/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class SplashPresenterTest: BaseSchedulersTest() {
	@Mock private lateinit var view: SplashContract.View
	@Mock private lateinit var getCountOfProductsUseCase: GetCountOfProductsDBUseCase
	@Mock private lateinit var loadProductsUseCase: LoadProductsUseCase
	@Mock private lateinit var saveProductsDBUseCase: SaveProductsDBUseCase
	@Mock private lateinit var context: Application

	@Captor private lateinit var exceptionArgumentsCaptor: ArgumentCaptor<String>

	private lateinit var splashPresenter: SplashPresenter

	@Before
	fun setupStatisticsPresenter() {
		MockitoAnnotations.initMocks(this)

		// Get a reference to the class under test
		splashPresenter = SplashPresenter(
				getCountOfProductsUseCase,
				loadProductsUseCase,
				saveProductsDBUseCase,
				context)
	}

	@Test
	fun loadProducts_countZero_CallNavigateNext() {
		val count = 0
		`when`(getCountOfProductsUseCase.execute()).thenReturn(Observable.just<Int>(count))

		val response = prepareProductResponse()
		`when`(loadProductsUseCase.execute()).thenReturn(Observable.just<ProductsResponse>(response))
		`when`(saveProductsDBUseCase.execute()).thenReturn(Observable.just<Boolean>(true))

		splashPresenter.takeView(view)
		verify(view).navigateToNext()
	}

	@Test
	fun loadProducts_countNotZero_CallNavigateNext() {
		val count = 1
		`when`(getCountOfProductsUseCase.execute()).thenReturn(Observable.just<Int>(count))

		splashPresenter.takeView(view)
		verify(view).navigateToNext()
	}

	@Test
	fun loadProducts_countZero_returnFalse_ShowError() {
		val count = 0
		`when`(getCountOfProductsUseCase.execute()).thenReturn(Observable.just<Int>(count))

		val response = prepareProductResponse()
		`when`(loadProductsUseCase.execute()).thenReturn(Observable.just<ProductsResponse>(response))
		`when`(saveProductsDBUseCase.execute()).thenReturn(Observable.just<Boolean>(false))
		`when`(context.getString(ArgumentMatchers.anyInt())).thenReturn("Something went wrong")

		splashPresenter.takeView(view)
		verify(view).showError(capture(exceptionArgumentsCaptor))
	}

	@Test
	fun loadProducts_getCountOfProductsUseCase_exception_ShowError() {
		`when`(getCountOfProductsUseCase.execute()).thenReturn(Observable.error(Exception()))
		`when`(context.getString(ArgumentMatchers.anyInt())).thenReturn("Something went wrong")

		splashPresenter.takeView(view)
		verify(view).showError(capture(exceptionArgumentsCaptor))
	}

	@Test
	fun loadProducts_loadProductsUseCase_exception_ShowError() {
		val count = 0
		`when`(getCountOfProductsUseCase.execute()).thenReturn(Observable.just<Int>(count))
		`when`(loadProductsUseCase.execute()).thenReturn(Observable.error(Exception()))
		`when`(context.getString(ArgumentMatchers.anyInt())).thenReturn("Something went wrong")

		splashPresenter.takeView(view)
		verify(view).showError(capture(exceptionArgumentsCaptor))
	}

	@Test
	fun loadProducts_saveProductsDBUseCase_exception_ShowError() {
		val count = 0
		`when`(getCountOfProductsUseCase.execute()).thenReturn(Observable.just<Int>(count))

		val response = prepareProductResponse()
		`when`(loadProductsUseCase.execute()).thenReturn(Observable.just<ProductsResponse>(response))

		`when`(saveProductsDBUseCase.execute()).thenReturn(Observable.error(Exception()))

		`when`(context.getString(ArgumentMatchers.anyInt())).thenReturn("Something went wrong")

		splashPresenter.takeView(view)
		verify(view).showError(capture(exceptionArgumentsCaptor))
	}

	private fun prepareProductResponse(): ProductsResponse {
		val products = ArrayList<ProductsResponse.Product>()
		val ids = ArrayList<Int>()
		ids.add(1)
		ids.add(2)
		ids.add(3)
		for (id in ids) {
			products.add(prepareResponseProduct(id))
		}
		return ProductsResponse(products)
	}
}