package com.project.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.base.R
import com.base.activities.BaseAppActivity
import com.base.util.replace
import com.google.gson.Gson
import com.project.ui.models.ItemPayment
import com.project.ui.screens.home.HomeFragment
import com.project.ui.screens.payment.PaymentFragment

/**
 * @author HungHN on 3/15/2018.
 */

class MainActivity : BaseAppActivity() {

    companion object{
        const val REQUEST_SCAN      = 1001
        const val EXTRA_DATA        = "com.blikoon.qrcodescanner.got_qr_scan_relult"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace(HomeFragment(), isAddToStack = false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        if (requestCode == REQUEST_SCAN){
            if (data == null) return

            val result = data.getStringExtra(EXTRA_DATA)
            val payment = Gson().fromJson(result, ItemPayment::class.java)
            if (!TextUtils.isEmpty(payment.paymentName)){
                replace(PaymentFragment.newInstance(payment))
            }
        }
    }
}
