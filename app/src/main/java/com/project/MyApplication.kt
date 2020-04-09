package com.project

import android.app.Application
import com.base.api.Api
import com.project.api.RetrofitApi

/**
 * @author HungHN on 3/15/2018.
 */
class MyApplication : Application() {

    lateinit var api: Api
    private set

    override fun onCreate() {
        super.onCreate()
        myApplicationInstance = this
        api = RetrofitApi()
    }

    companion object {

        private lateinit var myApplicationInstance: MyApplication

        fun get(): MyApplication {
            return myApplicationInstance
        }
    }
}
