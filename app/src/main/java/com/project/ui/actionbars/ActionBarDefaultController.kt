package com.project.ui.actionbars

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.basse.interfaces.ActionBarActionHandle
import com.app.basse.interfaces.ActionBarController
import com.base.R

/**
 * @author HungHN on 3/15/2018.
 */

class ActionBarDefaultController(private val actionBarActionHandle: ActionBarActionHandle) : ActionBarController {
    private var title: TextView? = null
    private var actionLeft: ImageView? = null

    override fun getActionBarLayout(): Int {
        return R.layout.actionbar_default
    }

    override fun findViews(parent: ViewGroup) {
        title = parent.findViewById(R.id.actionbarTitle)
        actionLeft = parent.findViewById(R.id.actionbarLeft)

        actionLeft?.setOnClickListener {
            actionBarActionHandle.doActionBarBack()
        }
    }

    override fun setUpViews() {
        title?.text = actionBarActionHandle.getTitle()
    }

    override fun setTitle(title: String) {
        this.title?.text = title
    }

}
