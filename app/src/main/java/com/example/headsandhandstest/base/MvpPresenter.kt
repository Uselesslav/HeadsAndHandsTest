package com.example.headsandhandstest.base

abstract class MvpPresenter<T : MvpView> {
    protected lateinit var view: T

    fun attachView(view: T) {
        this.view = view
    }
}