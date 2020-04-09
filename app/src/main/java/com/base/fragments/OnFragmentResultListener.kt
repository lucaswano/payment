package com.base.fragments

import android.content.Intent

/**
 * @author HungHN on 3/15/2018.
 */
interface OnFragmentResultListener {

    fun onFragmentResult(requestCode: Int, action: Int, extraData: Intent?)

}
