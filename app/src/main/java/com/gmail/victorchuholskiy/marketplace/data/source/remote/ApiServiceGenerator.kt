package com.gmail.victorchuholskiy.marketplace.data.source.remote

import com.gmail.victorchuholskiy.marketplace.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 *
 * A method for generating REST Api services according to standard rules
 */


private const val CONNECTION_TIMEOUT_SEC = 30L // Connection timeout in seconds
private const val READ_TIMEOUT_SEC = 30L // Read timeout in seconds

private val logger: HttpLoggingInterceptor
	get() {
		val loggingInterceptor = HttpLoggingInterceptor()
		if (BuildConfig.DEBUG) {
			loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
		}
		return loggingInterceptor
	}

fun <T> createService(serviceClass: Class<T>,
					  baseUrl: String,
					  gson: Gson): T {
	val mOkHttpBuilder = OkHttpClient.Builder()
			.connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
			.readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
			.addInterceptor(logger)

	return Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.client(mOkHttpBuilder.build())
			.build()
			.create(serviceClass)
}