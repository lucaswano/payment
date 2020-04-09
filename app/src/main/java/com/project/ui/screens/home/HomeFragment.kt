package com.project.ui.screens.home

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.base.R
import com.base.fragments.BaseAppFragment
import com.project.ui.preferences.UserPrefs
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseAppFragment() {

    override val layoutRes = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeVP.adapter = HomePagerAdapter(childFragmentManager)
        homeTab.setupWithViewPager(homeVP)
        setTabIcons()
        //getListUser()
    }

    private fun setTabIcons(){
        val tabOne = TextView(context)
        tabOne.gravity = Gravity.CENTER
        tabOne.text = "Wallet"
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_wallet, 0, 0)
        homeTab.getTabAt(0)?.customView = tabOne

        val tabTwo = TextView(context)
        tabTwo.gravity = Gravity.CENTER
        tabTwo.text = "Scan"
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_scan, 0, 0)
        homeTab.getTabAt(1)?.customView = tabTwo

        val tabThree = TextView(context)
        tabThree.gravity = Gravity.CENTER
        tabThree.text = "Profile"
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_profile, 0, 0)
        homeTab.getTabAt(2)?.customView = tabThree
    }

    private fun getListUser(){
        disposable.add(dbManager.getListUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listUser ->
                    Log.e("listUser", listUser.toString())
                }, { error ->
                    Log.e("Unknown Error", "Error !!!")
                }))
    }
}