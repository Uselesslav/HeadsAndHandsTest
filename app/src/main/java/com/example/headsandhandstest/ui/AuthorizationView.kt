package com.example.headsandhandstest.ui

import com.example.headsandhandstest.base.MvpView

interface AuthorizationView : MvpView {
    fun showEmailNotValidError()
    fun showPasswordMustContainAtLeastOneLowercaseLetterError()
    fun showPasswordMustContainAtLeastOneCapitalLetterError()
    fun showPasswordMustContainAtLeastOneDigitError()
    fun showPasswordMustContainAtLeastSixCharactersError()

    fun showSignInSuccess()

    fun showKeyboardOnEmail()
    fun showKeyboardOnPassword()
    fun closeKeyboard()

    fun clearValidationMessages()

    fun showCreateMessage()

    fun close()
}