package com.project.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.db.DBConst
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query("Select * from userDB")
    fun getListUser(): Flowable<List<User>>

    @Insert
    fun insertUser(user: User)

    @Query("Select * from userDB where email=:email")
    fun getUser(email: String): Flowable<User>

    @Query("DELETE from userDB where email=:email")
    fun deleteUser(email: String)

    @Query("Select email from userDB")
    fun getListEmail(): Flowable<List<String>>
}