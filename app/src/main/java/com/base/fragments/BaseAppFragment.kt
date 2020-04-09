package com.base.fragments

/**
 * @author HungHN
 */

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.basse.interfaces.ActionBarActionHandle
import com.app.basse.interfaces.ActionBarController
import com.app.basse.interfaces.ActionBarLayout
import com.base.R
import com.base.activities.host.AppFragmentHost
import com.base.api.Api
import com.base.callbacks.BackHandle
import com.base.dialog.LoadingDialogFragment
import com.base.slideMenu.ActionMenu
import com.base.util.goBack
import com.base.views.AppActionBar
import com.project.db.DbManager
import io.reactivex.disposables.CompositeDisposable

/**
 * @author HungHN on 3/15/2018.
 * This is base App fragment.
 * It contains some default attributes:  Api, menu
 * NavigationManager, Actionbar
 */
abstract class BaseAppFragment : androidx.fragment.app.Fragment(), ActionBarLayout, ActionBarActionHandle, BackHandle {

    var api: Api? = null

    var actionMenu: ActionMenu? = null

    protected lateinit var activity: Activity

    private var mHandler: Handler? = null

    @get:LayoutRes
    abstract val layoutRes: Int

    open var hasActionBar = false

    private var mProgressDialog: ProgressDialog? = null

    val dbManager = DbManager()
    val disposable = CompositeDisposable()

    init {
        arguments = Bundle()
    }

    @CallSuper
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as Activity
        if (context is AppFragmentHost) {
            api = context.api
            actionMenu = context.actionMenu
        }
    }

    /**
     * Shouldn't override this function...Use [.getLayoutRes]
     */
    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val content: View?
        val view = inflater.inflate(layoutRes, container, false)
        if (hasActionBar) {
            content = inflater.inflate(R.layout.layout_app_action_bar, container, false)
            (content as? ViewGroup)?.addView(view)
            val actionBar = content.findViewById(R.id.action_bar) as AppActionBar
            actionBar.init(this)
        } else
            content = view
        return content
    }

    protected fun showLoading() {
        val loading = childFragmentManager.findFragmentByTag(LOADING_TAG)
        if (loading != null) return

        LoadingDialogFragment().show(childFragmentManager, LOADING_TAG)
    }

    protected fun hideLoading() {

        if (mHandler == null) mHandler = Handler(Looper.getMainLooper())
        mHandler?.postDelayed(Runnable {
            val loading = childFragmentManager.findFragmentByTag(LOADING_TAG) ?: return@Runnable

            (loading as androidx.fragment.app.DialogFragment).dismissAllowingStateLoss()
        }, 500)
    }

    protected fun showDialog(dialog: androidx.fragment.app.DialogFragment?) {
        if (dialog == null) return

        dialog.show(childFragmentManager, DIALOG_TAG)
    }

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog?.setCancelable(false)
            mProgressDialog?.setMessage("Loading...")
        }

        mProgressDialog?.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog?.isShowing == true) {
            mProgressDialog?.dismiss()
        }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()

        mHandler?.removeCallbacksAndMessages(null)
        mHandler = null
    }

    companion object {

        val DIALOG_TAG = "dialog_tag"
        val LOADING_TAG = "loading_tag"
    }

    override fun doActionBarBack(): Boolean {
        return goBack()
    }

    override fun getTitle(): String {
        return ""
    }

    override fun doAction(actionCode: Int) {

    }

    override fun getActionBarController(): ActionBarController? {
        return null
    }

    override fun actionBack(): Boolean {
        return false
    }
}
