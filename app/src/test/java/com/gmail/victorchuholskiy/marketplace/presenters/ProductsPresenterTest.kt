package com.gmail.victorchuholskiy.marketplace.presenters

import com.gmail.victorchuholskiy.marketplace.base.BaseSchedulersTest
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.gmail.victorchuholskiy.marketplace.products.ProductsContract
import com.gmail.victorchuholskiy.marketplace.products.ProductsPresenter
import com.gmail.victorchuholskiy.marketplace.useCases.getProducts.GetProductsDBUseCase
import com.gmail.victorchuholskiy.marketplace.utils.capture
import com.gmail.victorchuholskiy.marketplace.utils.prepareProduct
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
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
class ProductsPresenterTest: BaseSchedulersTest() {
	@Mock private lateinit var view: ProductsContract.View
	@Mock private lateinit var getProductUseCase: GetProductsDBUseCase

	@Captor private lateinit var listArgumentsCaptor: ArgumentCaptor<List<Product>>
	@Captor private lateinit var exceptionArgumentsCaptor: ArgumentCaptor<Exception>

	private lateinit var productsPresenter: ProductsPresenter

	@Before
	fun setupStatisticsPresenter() {
		MockitoAnnotations.initMocks(this)

		// Get a reference to the class under test
		productsPresenter = ProductsPresenter(getProductUseCase)
	}

	@Test
	fun loadProducts_ShowList() {
		val list = ArrayList<Product>()
		val ids = ArrayList<Int>()
		ids.add(1)
		ids.add(2)
		ids.add(3)
		for (id in ids) {
			list.add(prepareProduct(id))
		}
		`when`(getProductUseCase.execute()).thenReturn(Observable.just<List<Product>>(list))

		productsPresenter.takeView(view)

		verify(view).showProgress()
		verify(view).showProduct(capture(listArgumentsCaptor))
		verify(view).hideProgress()

		val testObserver = TestObserver<List<Product>>()
		Observable.just(listArgumentsCaptor.value).subscribe(testObserver)
		testObserver.assertNoErrors()
				.assertValue {
					it.size == list.size
				}
				.assertValue {
					Observable.fromIterable(it)
							.map(Product::id)
							.toList()
							.blockingGet() == ids
				}
	}

	@Test
	fun loadProducts_exception_ShowError() {
		`when`(getProductUseCase.execute()).thenReturn(Observable.error(Exception()))

		productsPresenter.takeView(view)

		verify(view).showProgress()
		verify(view).showError(capture(exceptionArgumentsCaptor))
		verify(view).hideProgress()
	}
}