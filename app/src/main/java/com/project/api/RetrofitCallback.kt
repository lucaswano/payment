package com.project.api

import com.base.api.ApiError
import com.base.api.ApiSuccess
import com.project.api.errors.ParserError
import com.project.api.errors.ServerError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by HungHN on 3/16/18.
 */
class RetrofitCallback<T>(private val requestId: String,
                          private val apiError: ApiError,
                          private val apiSuccess: ApiSuccess<T>) : Callback<T> {

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        apiError.invoke(requestId, ServerError(requestId, t.toString()))
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        response?.body()?.let {
            apiSuccess.invoke(requestId, it)
            return
        }

        apiError.invoke(requestId, ParserError(requestId))
    }
}