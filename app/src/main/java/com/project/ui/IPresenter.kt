package com.project.ui

interface IPresenter<T : IView> {
    fun initView(view: T)
    fun destroyView()
}