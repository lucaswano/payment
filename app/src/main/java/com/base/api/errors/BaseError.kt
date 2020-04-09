package com.base.api.errors

/**
 * @author HungHN on 3/15/2018.
 */
open class BaseError(open val requestId: String, open val response: String) : Exception()
