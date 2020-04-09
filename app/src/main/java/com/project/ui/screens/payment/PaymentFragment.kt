package com.project.ui.screens.payment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.base.R
import com.base.fragments.BaseAppFragment
import com.base.util.goBack
import com.project.db.history.Payment
import com.project.ui.Const
import com.project.ui.customcomponent.PaymentSuccessDialog
import com.project.ui.models.ItemPayment
import com.project.ui.preferences.UserPrefs
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment: BaseAppFragment() {
    companion object{
        const val KEY_DATA = "key.data"
        const val REQUEST_SHOW_DIALOG = 100
        fun newInstance(item: ItemPayment) = PaymentFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_DATA, item)
            }
        }
    }

    override val layoutRes = R.layout.fragment_payment

    private var itemPayment: ItemPayment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemPayment = arguments?.getParcelable(KEY_DATA)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        imgPayment.setImageResource(when(itemPayment?.type){
            Const.TYPE_GOOGLE_PLAY -> R.drawable.icon_google_play
            Const.TYPE_COREL -> R.drawable.icon_corel
            Const.TYPE_DUELIST -> R.drawable.icon_duelist
            Const.TYPE_HELEN -> R.drawable.icon_helen
            else -> R.drawable.icon_steam
        })
        itemName.text = itemPayment?.paymentItem ?: ""
        paymentCompanyName.text = itemPayment?.paymentName ?: ""
        subTotal.text = getString(R.string.payment_amount, itemPayment?.totalAmount)
        fee.text = getString(R.string.payment_amount, itemPayment?.fee)
        currentAvailable.text = getString(R.string.payment_amount, UserPrefs.get().user?.balance ?: 0)
        totalAmount.text = getString(R.string.payment_amount, (itemPayment?.totalAmount ?: 0) + (itemPayment?.fee ?: 0))
        btnPayment.text = getString(R.string.pay_title, (itemPayment?.totalAmount ?: 0) + (itemPayment?.fee ?: 0))
        btnBack.setOnClickListener {
            goBack()
        }

        btnPayment.setOnClickListener {
            val item = Payment(id = UserPrefs.get().user?.id ?: 0,time =  System.currentTimeMillis(),paymentType = itemPayment?.type ?: 0,
                    paymentName = itemPayment?.paymentName ?: "",paymentItem =  itemPayment?.paymentItem ?: "",subAmount = itemPayment?.totalAmount ?: 0,fee = itemPayment?.fee ?: 0)
            disposable.add(Completable.fromAction{
                dbManager.insertPayment(item)
            }.subscribeOn(Schedulers.io()).subscribe())
            Handler().postDelayed({
                showDialogSuccess()
            }, 500)
        }
    }

    private fun showDialogSuccess() {
        val dialog = PaymentSuccessDialog()
        dialog.setTargetFragment(this, REQUEST_SHOW_DIALOG)
        dialog.show(fragmentManager, "success")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        if (requestCode == REQUEST_SHOW_DIALOG) goBack()
    }
}