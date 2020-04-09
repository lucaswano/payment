package com.base.api.wrapper

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.base.api.listener.ApiResponseListener

import java.lang.ref.SoftReference

/**
 * @author HungHN on 3/15/2018.
 * An implementation of [ApiResponseListener]
 *
 *
 * Use existed view to execute [ApiResponseListener.onResponse] on UI thread.
 */

class ApiUIResponseRS<in T>(responseListener: ApiResponseListener<T>) : ApiResponseListener<T> {

    private val rsReference: SoftReference<ApiResponseListener<T>> = SoftReference(responseListener)

    override fun onResponse(requestId: String, response: T) {
        val responseListener = rsReference.get()

        if (responseListener == null) {
            Log.d("ApiUIResponseRS", "Response Listener is null")
            return
        }

        sUIThreadHandler.post { responseListener.onResponse(requestId, response) }
    }

    override fun onError(requestId: String, e: Exception) {
        val responseListener = rsReference.get()

        if (responseListener == null) {
            Log.d("ApiUIResponseRS", "Response Listener is null")
            return
        }

        sUIThreadHandler.post { responseListener.onError(requestId, e) }

    }

    companion object {
        private val sUIThreadHandler = Handler(Looper.getMainLooper())
    }
}
