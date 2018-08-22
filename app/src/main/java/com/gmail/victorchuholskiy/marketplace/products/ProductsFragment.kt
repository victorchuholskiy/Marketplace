package com.gmail.victorchuholskiy.marketplace.products

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmail.victorchuholskiy.marketplace.R
import com.gmail.victorchuholskiy.marketplace.adapter.ProductsAdapter
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 */
@ActivityScoped
class ProductsFragment @Inject constructor(): Fragment(), ProductsContract.View {

	@Inject
	lateinit var presenter: ProductsContract.Presenter

	private val productsAdapter = ProductsAdapter(ArrayList(), listener = {
		showProductDetailsListener!!.showDetails(it.url, it.name)
	})

	private lateinit var rvProducts: RecyclerView
	private lateinit var srlRefresh: SwipeRefreshLayout

	private var showProductDetailsListener: ProductDetailsListener? = null

	override fun onAttach(context: Context?) {
		super.onAttach(context)
		if (context is ProductDetailsListener) {
			showProductDetailsListener = context
		}
	}

	override fun onCreateView(inflater: LayoutInflater,
							  container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_products, container, false)

		val productsLayoutManager = LinearLayoutManager(context)

		with(view) {
			rvProducts = findViewById<RecyclerView>(R.id.rv_products).apply {
				setHasFixedSize(true)
				layoutManager = productsLayoutManager
				adapter = productsAdapter
			}

			srlRefresh = findViewById<SwipeRefreshLayout>(R.id.srl_refresh).apply {
				setColorSchemeColors(
						android.support.v4.content.ContextCompat.getColor(requireContext(), R.color.colorPrimary),
						android.support.v4.content.ContextCompat.getColor(requireContext(), R.color.colorAccent)
				)
				setOnRefreshListener { presenter.updateProductsFromServer() }
			}
		}
		return view
	}

	override fun onResume() {
		super.onResume()
		presenter.takeView(this)
		presenter.loadProducts()
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.dropView()
	}

	override fun showProduct(products: List<Product>) {
		productsAdapter.setData(products)
	}

	override fun showProgress() {
		srlRefresh.isRefreshing = true
	}

	override fun hideProgress() {
		srlRefresh.isRefreshing = false
	}

	override fun showError(exception: Throwable?) {
		Toast.makeText(context, exception?.message , Toast.LENGTH_SHORT).show()
	}
}