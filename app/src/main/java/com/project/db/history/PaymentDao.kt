package com.project.db.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface PaymentDao {
    @Query("Select * from paymentDB")
    fun getHistory(): Flowable<List<Payment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPayment(item: Payment)

    @Query("Select * from paymentDB where userId=:userId")
    fun getHistoryByUser(userId: Int): Flowable<List<Payment>>
}