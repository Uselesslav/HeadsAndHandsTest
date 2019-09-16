package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.ui.AuthorizationPresenter
import com.example.headsandhandstest.authorization.ui.AuthorizationPresenterImplementation
import com.example.headsandhandstest.authorization.ui.AuthorizationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AuthorizationPresenterTest {
    companion object {
        private const val CORRECT_EMAIL = "qwer@fsd.cw"
        private const val CORRECT_PASSWORD = "QWer12"
        private const val SIGN_IN_RESULT = "Weather"
    }

    private val testCoroutineScope = TestCoroutineScope()
    private val testDispatcher = TestCoroutineDispatcher()
    private val authorizationPresenter: AuthorizationPresenter =
        AuthorizationPresenterImplementation(
            AuthorizationInteractorFactory().create(SIGN_IN_RESULT),
            testCoroutineScope,
            Dispatchers
        )
    private val mockAuthorizationView: AuthorizationView =
        Mockito.mock(AuthorizationView::class.java)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        authorizationPresenter.attachView(mockAuthorizationView)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun signIn() {
        testCoroutineScope.runBlockingTest {
            authorizationPresenter.signIn(CORRECT_EMAIL, CORRECT_PASSWORD)

            Mockito.verify(mockAuthorizationView).showLoadingIndicator()
            Mockito.verify(mockAuthorizationView).hideLoadingIndicator()
            Mockito.verify(mockAuthorizationView).closeKeyboard()
            Mockito.verify(mockAuthorizationView).showSignInSuccess(SIGN_IN_RESULT)
        }
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