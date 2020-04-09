package com.project.ui.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ItemPayment: Parcelable {
    @SerializedName("type")
    val type: Int = 0
    @SerializedName("payment_name")
    val paymentName: String = ""
    @SerializedName("payment_item")
    val paymentItem: String = ""
    @SerializedName("total_amount")
    val totalAmount: Int = 0
    @SerializedName("fee")
    val fee: Int = 0
}