package com.example.headsandhandstest.authorization.ui

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

    fun clearEmailValidationMessage()
    fun clearPasswordValidationMessage()

    fun setSignInButtonEnabled()
    fun setSignInButtonDisabled()

    fun showCreateMessage()

    // TODO: Move to Navigator
    fun close()
}