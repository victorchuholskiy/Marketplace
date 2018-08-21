package com.gmail.victorchuholskiy.marketplace.products

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.victorchuholskiy.marketplace.R
import com.gmail.victorchuholskiy.marketplace.di.ActivityScoped
import com.gmail.victorchuholskiy.marketplace.utils.ARG_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*
import javax.inject.Inject

/**
 * Created by viktor.chukholskiy
 * 10/08/18.
 */
@ActivityScoped
class DetailsFragment @Inject constructor(): Fragment() {

	override fun onCreateView(inflater: LayoutInflater,
							  container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_details, container, false)
		val url = arguments!!.getString(ARG_URL)
		Picasso.get().load(url).into(view.zv_zoom)
		return view
	}
}