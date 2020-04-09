package com.base.slideMenu

import com.base.activities.NActivity

/**
 * @author HungHN on 3/15/2018.
 */

interface ActionMenu {

    val isShow: Boolean

    fun initialize(activity: NActivity)

    fun hide()

    fun show()

    fun destroy()
}
