package com.example.headsandhandstest.ui

import com.example.headsandhandstest.base.MvpPresenter

abstract class AuthorizationPresenter : MvpPresenter<AuthorizationView>() {
    abstract fun signIn(email: String, password: String)
    abstract fun create()
    abstract fun close()

    abstract fun onPause()
    abstract fun onResume()

    abstract fun changedEnteredEmail(email: String, password: String)
    abstract fun changedEnteredPassword(email: String, password: String)
}