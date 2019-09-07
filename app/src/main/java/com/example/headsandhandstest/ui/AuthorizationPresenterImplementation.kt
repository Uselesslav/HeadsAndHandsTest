package com.example.headsandhandstest.ui

import java.util.*

class AuthorizationPresenterImplementation : AuthorizationPresenter() {
    override fun onResume() {
        view.showKeyboardOnEmail()
    }

    override fun onPause() {
        view.closeKeyboard()
    }

    override fun changedEnteredEmail(email: String, password: String) {
        view.clearEmailValidationMessage()
        changeEnabledSignInButton(email, password)
    }

    override fun changedEnteredPassword(email: String, password: String) {
        view.clearPasswordValidationMessage()
        changeEnabledSignInButton(email, password)
    }

    override fun signIn(email: String, password: String) {
        if (!email.matches(Regex("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$"))) {
            view.showEmailNotValidError()
        } else if (password == password.toLowerCase(Locale.getDefault())) {
            view.showPasswordMustContainAtLeastOneCapitalLetterError()
        } else if (password == password.toUpperCase(Locale.getDefault())) {
            view.showPasswordMustContainAtLeastOneLowercaseLetterError()
        } else if (password.length < 6) {
            view.showPasswordMustContainAtLeastSixCharactersError()
        } else if (!password.contains(Regex("[0-9]"))) {
            view.showPasswordMustContainAtLeastOneDigitError()
        } else {
            view.closeKeyboard()
            view.showSignInSuccess()
        }
    }

    override fun create() {
        view.showCreateMessage()
    }

    override fun close() {
        view.close()
    }

    private fun changeEnabledSignInButton(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            view.setSignInButtonEnabled()
        } else {
            view.setSignInButtonDisabled()
        }
    }
}