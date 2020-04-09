package com.project.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.db.DBConst

@Entity(tableName = DBConst.USER_TABLE)
class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "balance")
    val balance: Int
)