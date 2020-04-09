package com.base.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun showKeyboard(activity: Activity) {
    try {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(activity.currentFocus, InputMethodManager.SHOW_IMPLICIT)
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun hideKeyboard(activity: Activity): Boolean {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    try {
        if (imm.isAcceptingText) {
            return imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return false
}