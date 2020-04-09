package com.project.api

import com.project.ui.models.Demo
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author HungHN on 3/15/2018.
 */

interface RetrofitService {

    @GET("bins/44fql")
    fun  callApi() : Call<Demo>
}
