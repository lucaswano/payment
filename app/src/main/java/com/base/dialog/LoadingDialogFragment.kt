package com.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.base.R

/**
 * @author HungHN on 3/15/2018.
 */

class LoadingDialogFragment : NDialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }
}
