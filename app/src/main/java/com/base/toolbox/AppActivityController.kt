package com.base.toolbox

import android.view.KeyEvent
import com.base.activities.BaseAppActivity
import com.base.activities.NActivity
import com.base.activities.host.AppFragmentHost
import com.base.api.Api
import com.base.callbacks.BackHandle
import com.base.slideMenu.ActionMenu
import com.base.util.activePage
import com.base.util.goBack
import com.project.MyApplication


/**
 * @author HungHN on 3/15/2018.
 */
class AppActivityController(val activity: BaseAppActivity) : AppFragmentHost {

    private val mApi = MyApplication.get().api

    private var mActionMenu: ActionMenu? = null

    override val api: Api
        get() = mApi


    override val actionMenu: ActionMenu?
        get() = mActionMenu

    fun doOnInitMenu(activity: NActivity) {
        mActionMenu = AppDrawerMenu()
        mActionMenu?.initialize(activity)
    }

    fun doOnDestroy() {
    }

    /**
     * @return true if handled, else return false
     */
    fun doDuringOnKeyDown(keyCode: Int): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            callBackHandlerOnActivePage()
        } else false

    }

    fun doOnBackPress(): Boolean {
        return callBackHandlerOnActivePage()
    }

    private fun callBackHandlerOnActivePage(): Boolean {

        if (mActionMenu?.isShow == true) {
            mActionMenu?.hide()
            return true
        }

        return activity.goBack()
    }
}
