package com.gmail.victorchuholskiy.marketplace.useCases

import com.gmail.victorchuholskiy.marketplace.base.BaseSchedulersTest

import com.gmail.victorchuholskiy.marketplace.data.source.remote.RestClient
import com.gmail.victorchuholskiy.marketplace.data.source.remote.response.ProductsResponse
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCase
import com.gmail.victorchuholskiy.marketplace.useCases.loadProducts.LoadProductsUseCaseImpl
import com.gmail.victorchuholskiy.marketplace.utils.prepareResponseProduct
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.collections.ArrayList

/**
 * Created by viktor.chukholskiy
 * 22/08/18.
 */
@RunWith(MockitoJUnitRunner::class)
class LoadProductsUseCaseTest: BaseSchedulersTest() {
	@Mock private lateinit var restClient: RestClient

	private var loadProductsUseCase: LoadProductsUseCase? = null

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		loadProductsUseCase = LoadProductsUseCaseImpl(restClient)
	}

	@Test
	fun saveProductsTest_checkCorrectness() {
		val products = ArrayList<ProductsResponse.Product>()
		val ids = ArrayList<Int>()
		ids.add(1)
		ids.add(2)
		ids.add(3)
		for (id in ids) {
			products.add(prepareResponseProduct(id))
		}

		val response = ProductsResponse(products)

		Mockito.`when`(restClient.getProducts()).thenReturn(Observable.just<ProductsResponse>(response))

		val testObserver: TestObserver<ProductsResponse> = loadProductsUseCase!!.execute().test()
		testObserver
				.assertNoErrors()
				.assertValue { it.products != null && it.products!!.size == products.size }
				.assertValue {
					Observable.fromIterable(it.products)
							.map(ProductsResponse.Product::id)
							.toList()
							.blockingGet() == ids
				}
	}
}