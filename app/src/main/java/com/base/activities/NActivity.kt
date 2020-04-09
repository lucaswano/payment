package com.base.activities

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

/**
 * @author HungHN on 3/15/2018.
 * This is base activity for NTQ
 */
abstract class NActivity : AppCompatActivity() {

    protected var mSaveInstanceStateCalled: Boolean = false

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSaveInstanceStateCalled = false
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        mSaveInstanceStateCalled = false
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        mSaveInstanceStateCalled = false
    }

    fun canChangeFragmentManagerState(): Boolean {
        return !(mSaveInstanceStateCalled || isFinishing)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mSaveInstanceStateCalled = true
    }

    companion object {
        protected val TAG = "NTQActivity"
    }
}
