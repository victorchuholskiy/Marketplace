package com.gmail.victorchuholskiy.marketplace.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.gmail.victorchuholskiy.marketplace.data.source.local.entities.Product
import io.reactivex.Flowable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
@Dao
interface ProductsDao {

	@Query("SELECT * from product")
	fun getAll(): Flowable<List<Product>>

	@Query("SELECT COUNT(*) FROM product")
	fun getNumberOfRows(): Flowable<Int>

	@Insert(onConflict = REPLACE)
	fun insertAll(data: List<Product>)

	@Query("DELETE FROM product")
	fun deleteProducts()
}