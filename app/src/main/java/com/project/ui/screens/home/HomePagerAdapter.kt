package com.project.ui.screens.home

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.project.ui.screens.home.view.ProfileFragment
import com.project.ui.screens.home.view.ScanFragment
import com.project.ui.screens.home.view.WalletFragment

class HomePagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int) = when(position){
        0 -> WalletFragment()
        1 -> ScanFragment()
        else -> ProfileFragment()
    }

    override fun getCount() = 3
}