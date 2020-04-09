package com.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.app.basse.interfaces.ActionBarContent
import com.app.basse.interfaces.ActionBarController
import com.app.basse.interfaces.ActionBarLayout

/**
 * Created by NTH1991 on 7/2/2018.
 */
class AppActionBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), ActionBarContent {
    private var actionBarController: ActionBarController? = null
    override fun init(actionBarContent: ActionBarLayout) {
        actionBarController = actionBarContent.getActionBarController()
        inflateActionBarView()
    }

    private fun inflateActionBarView() {
        actionBarController?.run {
            View.inflate(context, getActionBarLayout(), this@AppActionBar)
            findViews(this@AppActionBar)
            setUpViews()
        }

    }

    override fun setTitle(title: String) {
        actionBarController?.setTitle(title)
    }

}