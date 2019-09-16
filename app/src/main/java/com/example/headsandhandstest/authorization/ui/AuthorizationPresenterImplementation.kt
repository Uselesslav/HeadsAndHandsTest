package com.example.headsandhandstest.authorization.ui

import com.example.headsandhandstest.authorization.application.AuthorizationInteractor
import com.example.headsandhandstest.authorization.application.EmailValidationError
import com.example.headsandhandstest.authorization.application.PasswordValidationError
import com.example.headsandhandstest.authorization.application.SignInDto
import com.example.headsandhandstest.kernel.application.ValidationException
import com.example.headsandhandstest.kernel.infrastructure.NoConnectionException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationPresenterImplementation(
    private val authorizationInteractor: AuthorizationInteractor,
    private val coroutineScope: CoroutineScope,
    private val dispatchers: Dispatchers
) :
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
        coroutineScope.launch(dispatchers.Main) {
            try {
                view.showLoadingIndicator()
                val result =
                    withContext(dispatchers.IO) {
                        authorizationInteractor.signIn(SignInDto(email, password))
                    }
                view.closeKeyboard()
                view.showSignInSuccess(result)
            } catch (validationException: ValidationException) {
                catchValidationError(validationException.errors)
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

    private fun catchValidationError(errors: List<Any>) {
        errors.forEach {
            when (it) {
                is EmailValidationError -> showEmailValidationError(it)
                is PasswordValidationError -> showPasswordValidationError(it)
                else -> view.showUnknownError()
            }
        }
    }

    private fun showEmailValidationError(error: EmailValidationError) {
        when (error) {
            EmailValidationError.NOT_VALID -> view.showEmailNotValidError()
        }
    }

    private fun showPasswordValidationError(error: PasswordValidationError) {
        when (error) {
            PasswordValidationError.NOT_CONTAIN_AT_LEAST_SIX_CHARACTERS -> view.showPasswordMustContainAtLeastSixCharactersError()
            PasswordValidationError.NOT_CONTAIN_CAPITAL_LETTER -> view.showPasswordMustContainAtLeastOneCapitalLetterError()
            PasswordValidationError.NOT_CONTAIN_LOWERCASE_LETTER -> view.showPasswordMustContainAtLeastOneLowercaseLetterError()
            PasswordValidationError.NOT_CONTAIN_DIGIT -> view.showPasswordMustContainAtLeastOneDigitError()
        }
    }
}