package com.gmail.victorchuholskiy.marketplace.data.source.local.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 */
@Entity(tableName = "product")
data class Product(@PrimaryKey() var id: Int,
					   @ColumnInfo(name = "name") var name: String,
					   @ColumnInfo(name = "brand") var brand: String,
					   @ColumnInfo(name = "original_price") var originalPrice: Double,
					   @ColumnInfo(name = "current_price") var currentPrice: Double,
					   @ColumnInfo(name = "currency") var currency: String,
					   @ColumnInfo(name = "url") var url: String)