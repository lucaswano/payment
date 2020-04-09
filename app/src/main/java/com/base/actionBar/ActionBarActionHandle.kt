package com.app.basse.interfaces

/**
 * Created by NTH1991 on 7/3/2018.
 */
interface ActionBarActionHandle {
    fun doActionBarBack(): Boolean
    fun getTitle(): String
    fun doAction(actionCode: Int)
}