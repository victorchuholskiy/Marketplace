package com.gmail.victorchuholskiy.marketplace.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.gmail.victorchuholskiy.marketplace.data.source.local.dao.ProductsDao
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
@Database(entities = [Product::class], version = 1)
abstract class ProductsDataBase : RoomDatabase() {

	abstract fun productsDao(): ProductsDao

	companion object {
		private var INSTANCE: ProductsDataBase? = null

		fun getInstance(context: Context): ProductsDataBase? {
			if (INSTANCE == null) {
				synchronized(ProductsDataBase::class) {
					INSTANCE = Room.databaseBuilder(context.applicationContext,
							ProductsDataBase::class.java, "products_database")
							.build()
				}
			}
			return INSTANCE
		}

		fun destroyInstance() {
			INSTANCE = null
		}
	}
}