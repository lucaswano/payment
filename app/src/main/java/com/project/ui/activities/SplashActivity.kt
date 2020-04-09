package com.project.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.base.R
import com.base.util.replace
import com.project.ui.preferences.UserPrefs
import com.project.ui.screens.login.LoginFragment

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        //Handler().postDelayed({ MainActivity::class.start(this, true) }, 1000)
        if (UserPrefs.get().user?.id != null){
            startActivity(Intent(this, MainActivity::class.java))
        }else replace(LoginFragment(), holder = R.id.holderSplash, isAddToStack = false)
    }
}
