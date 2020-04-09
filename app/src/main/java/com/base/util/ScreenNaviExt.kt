package com.base.util

import android.app.Activity
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.base.R
import com.base.callbacks.BackHandle
import kotlin.reflect.KClass


enum class StyleAnimation {
    SLIDE_FROM_RIGHT, SLIDE_FROM_LEFT, SLIDE_FROM_BOTTOM, SLIDE_NONE
}

fun <T : Activity> KClass<T>.start(activity: Activity, finish: Boolean = false) {
    activity.startActivity(Intent(activity, this.java))
    if (finish) {
        activity.finish()
    }
}

fun <T : Activity> KClass<T>.startClearTop(activity: Activity) {
    val intent = Intent(activity, this.java)
    activity.startActivity(intent)
    activity.finishAffinity()

}

fun androidx.fragment.app.FragmentActivity.replace(fragment: androidx.fragment.app.Fragment, holder: Int = R.id.fragmentHolder, isAddToStack: Boolean = true, animation: StyleAnimation = StyleAnimation.SLIDE_NONE) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.run {
        setAnimation(this, animation)
        replace(holder, fragment)
        if (isAddToStack)
            addToBackStack(null)
        commit()
    }
}

fun androidx.fragment.app.FragmentActivity.add(fragment: androidx.fragment.app.Fragment, holder: Int = R.id.fragmentHolder, isAddToStack: Boolean = true, animation: StyleAnimation = StyleAnimation.SLIDE_NONE) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.run {
        setAnimation(this, animation)
        add(holder, fragment)
        if (isAddToStack)
            addToBackStack(null)
        commit()
    }
}

fun androidx.fragment.app.Fragment.replace(fragment: androidx.fragment.app.Fragment, holder: Int = R.id.fragmentHolder, isAddToStack: Boolean = true, animation: StyleAnimation = StyleAnimation.SLIDE_NONE) {
    val transaction = fragmentManager?.beginTransaction()
    transaction?.run {
        setAnimation(this, animation)
        replace(holder, fragment)
        if (isAddToStack)
            addToBackStack(null)
        commit()
    }
}

fun androidx.fragment.app.Fragment.add(fragment: androidx.fragment.app.Fragment, holder: Int = R.id.fragmentHolder, isAddToStack: Boolean = true, animation: StyleAnimation = StyleAnimation.SLIDE_NONE) {
    val transaction = fragmentManager?.beginTransaction()
    transaction?.run {
        setAnimation(this, animation)
        add(holder, fragment)
        if (isAddToStack)
            addToBackStack(null)

        commit()
    }
}

private fun setAnimation(transaction: androidx.fragment.app.FragmentTransaction, styleAnimation: StyleAnimation) {
    when (styleAnimation) {
        StyleAnimation.SLIDE_FROM_RIGHT -> transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        StyleAnimation.SLIDE_FROM_LEFT -> transaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right, R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        StyleAnimation.SLIDE_FROM_BOTTOM -> transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.stack_push, R.anim.stack_pop, R.anim.slide_out_bottom)
        else -> {
        }
    }
}

fun androidx.fragment.app.FragmentActivity.goBack(@IdRes holder: Int = R.id.fragmentHolder): Boolean {
    return if (!hideKeyboard(this) && supportFragmentManager.backStackEntryCount > 0) {
        var isHandled = false
        val activeFragment = activePage(holder)
        if (activeFragment is BackHandle) {
            isHandled = activeFragment.actionBack()
        }
        if (isHandled)
            return true


        supportFragmentManager.popBackStack()
        val transaction = supportFragmentManager.beginTransaction()
        val currentFragment = supportFragmentManager.findFragmentById(holder)
        if (currentFragment != null) {
            transaction.remove(currentFragment)
            transaction.commit()
        }
        true
    } else false
}

fun androidx.fragment.app.Fragment.goBack(): Boolean {
    return if (activity != null) {
        activity?.onBackPressed()
        true
    } else false


}

fun androidx.fragment.app.FragmentActivity.activePage(holder: Int = R.id.fragmentHolder): androidx.fragment.app.Fragment? {
    return supportFragmentManager.findFragmentById(holder)
}

fun androidx.fragment.app.Fragment.activePage(holder: Int = R.id.fragmentHolder): androidx.fragment.app.Fragment? {
    return fragmentManager?.findFragmentById(holder)
}