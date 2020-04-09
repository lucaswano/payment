package com.base.api.listener

/**
 * @author HungHN on 3/15/2018.
 */
interface ResponseListener<R> {

    @Throws(Exception::class)
    fun parse(requestId: String, response: String): R?

    fun onResponse(requestId: String, response: R)

    fun onError(requestId: String, e: Exception)
}
