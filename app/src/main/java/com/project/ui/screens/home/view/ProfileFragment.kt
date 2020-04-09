package com.project.ui.screens.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.base.R
import com.base.fragments.BaseAppFragment
import com.project.ui.activities.SplashActivity
import com.project.ui.preferences.UserPrefs
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: BaseAppFragment() {
    override val layoutRes = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileName.text = UserPrefs.get().user?.userName
        logout.setOnClickListener {
            UserPrefs.get().clear()
            activity.finish()
            startActivity(Intent(activity, SplashActivity::class.java))
        }
    }
}