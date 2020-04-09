package com.project.extensions

import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author HungHN on 3/15/2018.
 */


fun ViewGroup.inflate(@LayoutRes resource: Int): View = LayoutInflater.from(context).inflate(resource, this, false)