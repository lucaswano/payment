package com.base.toolbox

import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.base.R
import com.base.activities.NActivity
import com.base.slideMenu.ActionMenu


/**
 * @author HungHN on 3/15/2018.
 */

class AppDrawerMenu : ActionMenu, NavigationView.OnNavigationItemSelectedListener {

    private var drawer: DrawerLayout? = null
    private var activity: NActivity? = null

    override val isShow: Boolean
        get() = drawer != null && drawer?.isDrawerOpen(GravityCompat.START) == true

    override fun initialize(activity: NActivity) {
        this.activity = activity
        val toggle = ActionBarDrawerToggle(
                activity, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.addDrawerListener(toggle)
        toggle.syncState()

        /*val navigationView = activity.findViewById<NavigationView>(R.id.navigationView)
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this)
            initView(navigationView.getHeaderView(0))
        }*/
    }

    private fun initView(view: View) {
        // Todo findView
    }

    override fun hide() {
        if (drawer?.isDrawerOpen(GravityCompat.START) == true) {
            drawer?.closeDrawer(GravityCompat.START)
        }
    }

    override fun show() {
        drawer?.openDrawer(GravityCompat.START)
    }

    override fun destroy() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        drawer?.closeDrawer(GravityCompat.START)

        if (item.itemId == R.id.nav_home) {
        }
        return true
    }

}
