package com.project.api.errors

import com.base.api.errors.BaseError

/**
 * @author HungHN on 3/15/2018.
 */
data class ServerError(override val requestId: String,
                       override val response: String = "Server error") : BaseError(requestId, response)
