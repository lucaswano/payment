package com.project.api

import com.base.api.Api
import com.base.api.ApiError
import com.base.api.ApiSuccess
import com.project.ui.models.Demo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author HungHN on 3/15/2018.
 */

class RetrofitApi : Api {

    private var retrofitService: RetrofitService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    override fun callApi(requestId: String, params: String, apiError: ApiError, apiSuccess: ApiSuccess<Demo>) {
        val call = retrofitService.callApi()
        call.enqueue(RetrofitCallback<Demo>(requestId,apiError,apiSuccess))
    }
}
