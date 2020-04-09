package com.project.ui.screens.sample.view

import android.os.Bundle
import android.view.View
import com.base.R
import com.base.fragments.BaseAppFragment
import com.project.ui.screens.sample.presenter.ISamplePresenter
import com.project.ui.screens.sample.presenter.SamplePresenter

class SampleFragment : BaseAppFragment(), ISampleView {
    lateinit var iSamplePresenter: ISamplePresenter

    override val layoutRes: Int
        get() = R.layout.fragment_sample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iSamplePresenter = SamplePresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iSamplePresenter.initView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        iSamplePresenter.destroyView()
    }
}