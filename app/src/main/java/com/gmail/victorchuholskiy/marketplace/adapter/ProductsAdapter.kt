package com.gmail.victorchuholskiy.marketplace.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.victorchuholskiy.marketplace.R
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * Created by viktor.chukholskiy
 * 21/08/18.
 */
class ProductsAdapter(private var items: MutableList<Product>, private val listener: (Product) -> Unit) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

	override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

	override fun getItemCount() = items.size

	fun setData(products: List<Product>) {
		items.clear()
		items.addAll(products)
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: Product, listener: (Product) -> Unit) = with(itemView) {
			itemView.tv_name.text = item.name
			itemView.tv_brand.text = item.brand
			Picasso.get()
					.load(item.url)
					.error(R.drawable.ic_placeholder)
					.placeholder(R.drawable.ic_placeholder)
					.into(itemView.iv_photo)
			setOnClickListener { listener(item) }
			val strCurrentPrice = "${resources.getString(R.string.current_price)}: ${item.currentPrice} ${item.currency}"
			val strOriginalPrice = "${resources.getString(R.string.original_price)}: ${item.originalPrice} ${item.currency}"
			itemView.tv_current_price.text = strCurrentPrice
			itemView.tv_original_price.text = strOriginalPrice
			itemView.tv_original_price.visibility = if (item.currentPrice == item.originalPrice) View.INVISIBLE else View.VISIBLE
		}
	}
}