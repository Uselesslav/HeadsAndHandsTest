package com.example.headsandhandstest.authorization.ui

import com.example.headsandhandstest.authorization.application.AuthorizationInteractor
import com.example.headsandhandstest.authorization.application.SignInDto
import com.example.headsandhandstest.authorization.application.SignInValidationError
import com.example.headsandhandstest.kernel.application.ValidationException
import com.example.headsandhandstest.kernel.infrastructure.NoConnectionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationPresenterImplementation(private val authorizationInteractor: AuthorizationInteractor) :
    AuthorizationPresenter() {
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
        GlobalScope.launch(Dispatchers.Main) {
            try {
                view.showLoadingIndicator()
                val result =
                    withContext(Dispatchers.IO) {
                        authorizationInteractor.signIn(SignInDto(email, password))
                    }
                view.closeKeyboard()
                view.showSignInSuccess(result)
            } catch (validationException: ValidationException) {
                catchValidationError(validationException.errors as MutableList<SignInValidationError>)
            } catch (noConnectionException: NoConnectionException) {
                view.showNoConnectionError()
            } catch (exception: Exception) {
                view.showUnknownError()
            } finally {
                view.hideLoadingIndicator()
            }
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

    private fun catchValidationError(errors: List<SignInValidationError>) {
        errors.forEach {
            if (it == SignInValidationError.EMAIL_NOT_VALID) {
                view.showEmailNotValidError()
            }

            if (it == SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_SIX_CHARACTERS) {
                view.showPasswordMustContainAtLeastSixCharactersError()
            } else if (it == SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_CAPITAL_LETTER) {
                view.showPasswordMustContainAtLeastOneCapitalLetterError()
            } else if (it == SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_LOWERCASE_LETTER) {
                view.showPasswordMustContainAtLeastOneLowercaseLetterError()
            } else if (it == SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_DIGIT) {
                view.showPasswordMustContainAtLeastOneDigitError()
            }
        }
    }
}