package com.base.api.wrapper

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.base.api.listener.ResponseListener

import java.lang.ref.SoftReference

/**
 * @author HungHN on 3/15/2018.
 * An implementation of [ResponseListener]
 *
 *
 * Use existed view to execute [ResponseListener.onResponse] on UI thread.
 */

class UIResponseRS<T>(responseListener: ResponseListener<T>) : ResponseListener<T> {

    private val rsReference: SoftReference<ResponseListener<T>> = SoftReference(responseListener)

    @Throws(Exception::class)
    override fun parse(requestId: String, response: String): T? {
        val listener = rsReference.get()

        return listener?.parse(requestId, response)

    }

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
