package com.base.dialog.listener

import android.content.Intent

/**
 * @author HungHN on 3/15/2018.
 */
interface OnActionInDialogListener {
    /**
     * @param requestCode the request code
     * @param action      action from dialog
     * @param extraData   the extra data that will be passed to the source fragment
     */
    fun onDialogResult(requestCode: Int, action: Int, extraData: Intent?)
}
