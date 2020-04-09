package com.base.api

import com.base.api.errors.BaseError
import com.project.ui.models.Demo


/**
 * @author HungHN on 3/15/2018.
 * define fun request api
 */

internal typealias ApiError = (requestId: String, e: BaseError) -> Unit
internal typealias ApiSuccess<T> = (requestId: String, T) -> Unit

interface Api {

    fun callApi(requestId: String, params:String, apiError: ApiError, apiSuccess: ApiSuccess<Demo>)
}
