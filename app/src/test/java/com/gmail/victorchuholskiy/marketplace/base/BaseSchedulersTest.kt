package com.gmail.victorchuholskiy.marketplace.base

import android.support.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


/**
 * Created by victor.chuholskiy
 * 22/08/18.
 */
open class BaseSchedulersTest {
	@Before
	fun setUpRxSchedulers() {
		val immediate = object : Scheduler() {
			override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
				return super.scheduleDirect(run, 0, unit)
			}

			override fun createWorker(): Worker {
				return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
			}
		}
		RxJavaPlugins.setInitIoSchedulerHandler { immediate }
		RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
		RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
		RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
		RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
	}

	@After
	fun resetUpRxSchedulers() {
		RxJavaPlugins.reset()
		RxAndroidPlugins.reset()
	}
}