package com.gmail.victorchuholskiy.marketplace.data.useCases

import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 18/08/18.
 *
 * Base use case
 */
interface UseCase<PARAMS, RESPONSE> {
	fun execute(params: PARAMS): Observable<RESPONSE>


}