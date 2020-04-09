package com.project.ui.screens.sample.presenter

import com.project.ui.screens.sample.view.ISampleView

class SamplePresenter : ISamplePresenter {
    var sampleView: ISampleView? = null
    override fun initView(view: ISampleView) {
        sampleView = view
    }

    override fun destroyView() {
        sampleView = null
    }
}