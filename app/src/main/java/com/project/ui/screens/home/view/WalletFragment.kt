package com.project.ui.screens.home.view

import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.R
import com.base.fragments.BaseAppFragment
import com.project.db.history.Payment
import com.project.ui.preferences.UserPrefs
import com.project.ui.screens.home.adapter.AdapterWallet
import com.project.ui.screens.home.model.ItemHeader
import com.project.ui.screens.home.model.ItemWallet
import com.project.ui.screens.home.model.WalletObj
import com.project.ui.screens.payment.PaymentFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_wallet.*
import kotlinx.android.synthetic.main.item_header_wallet.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WalletFragment: BaseAppFragment() {
    override val layoutRes = R.layout.fragment_wallet

    private lateinit var adapter: AdapterWallet

    val formateDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    private val hashPay = linkedMapOf<String, ArrayList<Payment>>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterWallet()
        listWallet.layoutManager = LinearLayoutManager(context)
        listWallet.adapter = adapter
        getDataFormDb()
        setBalance()
    }

    private fun setBalance(){
        val balance = getString(R.string.payment_amount, UserPrefs.get().user?.balance ?: 0)
        val spannableString = SpannableString(balance)
        spannableString.setSpan(RelativeSizeSpan(0.7f), 0, 2, 0)
        money.text = spannableString
    }

    private fun getDataFormDb(){
        disposable.add(dbManager.getUserPayment(UserPrefs.get().user?.id ?: -1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    val listWallet = arrayListOf<WalletObj>()
                    list.forEach { item ->
                        if (!hashPay.containsKey(convertLongToStringData(item.time))) hashPay[convertLongToStringData(item.time)] = arrayListOf()
                        hashPay[convertLongToStringData(item.time)]?.add(item)
                    }

                    for (key in hashPay.keys){
                        listWallet.add(ItemHeader(key))
                        hashPay[key]?.forEach {payment ->
                            listWallet.add(ItemWallet(type = payment.paymentType,
                                    itemName = payment.paymentItem,
                                    companyName = payment.paymentName,
                                    time = payment.time,
                                    subTotal = payment.subAmount,
                                    fee = payment.fee))
                        }
                    }
                    adapter.setData(listWallet)
                }, { error ->
                    Log.e("Unknown Error", "Error !!!")
                }))
    }

    fun convertLongToStringData(long: Long) = formateDate.format(Date(long))
}