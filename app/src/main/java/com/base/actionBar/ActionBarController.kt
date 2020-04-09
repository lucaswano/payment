package com.app.basse.interfaces

import androidx.annotation.LayoutRes
import android.view.ViewGroup

/**
 * Created by NTH1991 on 7/2/2018.
 */
interface ActionBarController {
    @LayoutRes
    fun getActionBarLayout(): Int

    fun findViews(parent: ViewGroup)
    fun setUpViews()
    fun setTitle(title: String)
}