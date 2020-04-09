package com.project.ui.preferences

import android.content.SharedPreferences
import com.base.prefers.PreferenceHelper
import com.base.prefers.get
import com.base.prefers.set
import com.project.MyApplication
import com.project.db.user.User


/**
 * @author by HungHN on 3/22/18.
 *
 */
class UserPrefs {

    private val FILE_NAME = "Payment.app"
    private var prefs: SharedPreferences

    //define key here
    private val KEY_USER_NAME = "key.user.name"
    private val KEY_USER     = "key.user"
    init {
        prefs = PreferenceHelper.newPrefs(MyApplication.get(), FILE_NAME)
    }

    companion object {

        private var userPrefs = UserPrefs()

        fun get(): UserPrefs {
            return userPrefs
        }
    }

    fun setOnPreferencesChange(onSharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    fun unRegisterBroadcastPreferences(onSharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    fun clear(){
        prefs.edit().clear().commit()
    }

    var user: User?
        set(value) {
            prefs[KEY_USER] = value!!
        }
        get() = prefs[KEY_USER]

}