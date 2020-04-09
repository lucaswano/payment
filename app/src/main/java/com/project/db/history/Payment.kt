package com.project.db.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.db.DBConst

@Entity(tableName = DBConst.PAYMENT_TABLE)
class Payment(
    @PrimaryKey(autoGenerate = true)
    val keyId: Int = 0,
    @ColumnInfo(name = "userId")
    val id: Int = 0,
    @ColumnInfo(name = "time")
    val time: Long = 0,
    @ColumnInfo(name = "type")
    val paymentType: Int = 0,
    @ColumnInfo(name = "payment_name")
    val paymentName: String = "",
    @ColumnInfo(name = "payment_item")
    val paymentItem: String,
    @ColumnInfo(name = "total_amount")
    val subAmount: Int,
    @ColumnInfo(name = "fee")
    val fee: Int
)