package com.project.ui.customcomponent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.base.R
import kotlinx.android.synthetic.main.dialog_success.*

class PaymentSuccessDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_success, container, false)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        val displayMetrics = DisplayMetrics()
        dialog?.window?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        window?.setLayout(displayMetrics.widthPixels * 8 / 10, displayMetrics.heightPixels * 7 / 10)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOk.setOnClickListener {
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, Intent())
            dismiss()
        }
    }
}