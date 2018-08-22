package com.gmail.victorchuholskiy.marketplace

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
interface BasePresenter<T> {

	/**
	 * Binds presenter with a view when resumed. The Presenter will perform initialization here.
	 *
	 * @param view the view associated with this presenter
	 */
	abstract fun takeView(view: T)

	/**
	 * Drops the reference to the view when destroyed
	 */
	abstract fun dropView()
}