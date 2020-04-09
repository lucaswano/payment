package com.project.api.errors

import com.base.api.errors.BaseError

/**
 * @author HungHN on 3/15/2018.
 */
data class AuthFailureError(override val requestId: String,
                            override val response: String = "Authentication Failure") : BaseError(requestId, response)
