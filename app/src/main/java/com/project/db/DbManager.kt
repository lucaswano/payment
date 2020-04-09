package com.project.db

import com.project.db.history.Payment
import com.project.db.user.User

class DbManager {
    private val userDao = UserDB.getInstance().userDao()
    private val paymentDao = UserDB.getInstance().paymentDao()

    // Query user
    fun getListUser() = userDao.getListUser()

    fun createUser(user: User){
        userDao.insertUser(user)
    }

    fun getUser(email: String) = userDao.getUser(email)

    fun deleteUser(email: String) = userDao.deleteUser(email)

    fun getListEmail() = userDao.getListEmail()

    // Query payment

    fun getListPayment() = paymentDao.getHistory()

    fun insertPayment(item: Payment) = paymentDao.insertPayment(item)

    fun getUserPayment(userId: Int) = paymentDao.getHistoryByUser(userId)
}