package com.gmail.victorchuholskiy.marketplace.useCases

import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 *
 * Base use case
 */
interface UseCase<RESPONSE> {
	fun execute(): Observable<RESPONSE>


}