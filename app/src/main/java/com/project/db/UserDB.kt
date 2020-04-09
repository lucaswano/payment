package com.project.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.MyApplication
import com.project.db.history.Payment
import com.project.db.history.PaymentDao
import com.project.db.user.User
import com.project.db.user.UserDao

@Database(entities = [User::class, Payment::class], exportSchema = false, version = 3)
abstract class UserDB: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun paymentDao(): PaymentDao

    companion object{
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getInstance(): UserDB = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDatabase(MyApplication.get()).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        UserDB::class.java,DBConst.DB_NAME)
                        .fallbackToDestructiveMigration().build()
    }

    /**
     * Switches the internal implementation with an empty in-memory database.
     *
     * @param context The context.
     */
    @VisibleForTesting
    fun switchToInMemory(context: Context) {
        INSTANCE = Room.inMemoryDatabaseBuilder(context,
                UserDB::class.java).build()
    }
}