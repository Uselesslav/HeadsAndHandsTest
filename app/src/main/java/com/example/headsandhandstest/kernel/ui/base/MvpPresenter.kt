package com.example.headsandhandstest.kernel.ui.base

abstract class MvpPresenter<T : MvpView> {
    protected lateinit var view: T

    fun attachView(view: T) {
        this.view = view
    }
}