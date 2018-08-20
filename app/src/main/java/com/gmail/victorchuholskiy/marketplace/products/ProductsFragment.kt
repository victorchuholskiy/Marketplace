package com.gmail.victorchuholskiy.marketplace.products

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.moovel.android.coding.challenge.R
import com.moovel.android.coding.challenge.adapters.UsersAdapter
import com.moovel.android.coding.challenge.data.models.UserSearchModel
import com.moovel.android.coding.challenge.di.ActivityScoped
import com.moovel.android.coding.challenge.userDetails.UserDetailsActivity
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 */
@ActivityScoped
class ProductsFragment @Inject constructor(): Fragment(), ProductsContract.View {

	@Inject
	lateinit var presenter: ProductsContract.Presenter

	private lateinit var rvUsers: RecyclerView
	private lateinit var srlRefresh: SwipeRefreshLayout

	private val usersAdapter = UsersAdapter(ArrayList(), listener = {openDetailsActivity(it.login)})
	private val infiniteScrollListener = InfiniteScrollListener(func = { presenter.loadUsersPage(usersAdapter.page + 1) })

	override fun onCreateView(inflater: LayoutInflater,
							  container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_users, container, false)

		val imagesLayoutManager = LinearLayoutManager(context)

		with(view) {
			rvUsers = findViewById<RecyclerView>(R.id.rv_users).apply {
				setHasFixedSize(true)
				layoutManager = imagesLayoutManager
				adapter = usersAdapter
				addOnScrollListener(infiniteScrollListener)
			}

			srlRefresh = findViewById<SwipeRefreshLayout>(R.id.srl_refresh).apply {
				setColorSchemeColors(
						android.support.v4.content.ContextCompat.getColor(requireContext(), R.color.colorPrimary),
						android.support.v4.content.ContextCompat.getColor(requireContext(), R.color.colorAccent)
				)
				setOnRefreshListener { presenter.loadUsersPage(1, clear = true) }
			}
		}
		return view
	}

	override fun onResume() {
		super.onResume()
		presenter.takeView(this)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.dropView()
	}

	override fun showUsers(images: List<UserSearchModel>, clear: Boolean) {
		if (clear) usersAdapter.setData(images) else usersAdapter.addData(images)
		infiniteScrollListener.isRefreshing = false
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

	private fun openDetailsActivity(userName: String) {
		startActivity(UserDetailsActivity.getIntent(activity!!, userName))
	}
}