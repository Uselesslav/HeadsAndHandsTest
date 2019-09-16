package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.ui.AuthorizationPresenter
import com.example.headsandhandstest.authorization.ui.AuthorizationPresenterImplementation
import com.example.headsandhandstest.authorization.ui.AuthorizationView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AuthorizationPresenterTest {
    private val authorizationPresenter: AuthorizationPresenter =
        AuthorizationPresenterImplementation(AuthorizationInteractorFactory().create())

    private val mockAuthorizationView: AuthorizationView =
        Mockito.mock(AuthorizationView::class.java)

    @Before
    fun attachView() {
        authorizationPresenter.attachView(mockAuthorizationView)
    }

    @Test
    fun close() {
        authorizationPresenter.close()
        Mockito.verify(mockAuthorizationView).close()
    }

    @Test
    fun create() {
        authorizationPresenter.create()
        Mockito.verify(mockAuthorizationView).showCreateMessage()
    }

    @Test
    fun onPause() {
        authorizationPresenter.onPause()
        Mockito.verify(mockAuthorizationView).closeKeyboard()
    }

    @Test
    fun onResume() {
        authorizationPresenter.onResume()
        Mockito.verify(mockAuthorizationView).showKeyboardOnEmail()
    }

    @Test
    fun changedEnteredEmail() {
        authorizationPresenter.changedEnteredEmail("sdfd", "sdfsdf")
        Mockito.verify(mockAuthorizationView).clearEmailValidationMessage()
        Mockito.verify(mockAuthorizationView).setSignInButtonEnabled()
    }

    @Test
    fun changedEnteredEmailWithEmptyFields() {
        authorizationPresenter.changedEnteredEmail("", "")
        Mockito.verify(mockAuthorizationView).clearEmailValidationMessage()
        Mockito.verify(mockAuthorizationView).setSignInButtonDisabled()
    }

    @Test
    fun changedEnteredPassword() {
        authorizationPresenter.changedEnteredPassword("sdfd", "sdfsdf")
        Mockito.verify(mockAuthorizationView).clearPasswordValidationMessage()
        Mockito.verify(mockAuthorizationView).setSignInButtonEnabled()
    }

    @Test
    fun changedEnteredPasswordWithEmptyFields() {
        authorizationPresenter.changedEnteredPassword("", "")
        Mockito.verify(mockAuthorizationView).clearPasswordValidationMessage()
        Mockito.verify(mockAuthorizationView).setSignInButtonDisabled()
    }
}