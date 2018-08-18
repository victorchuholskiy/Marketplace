package com.gmail.victorchuholskiy.marketplace.data.source.local.dao

import android.arch.persistence.room.Dao
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

	@Insert(onConflict = REPLACE)
	fun insert(data: Product)
}