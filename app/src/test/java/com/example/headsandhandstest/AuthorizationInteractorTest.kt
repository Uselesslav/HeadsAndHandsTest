package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.SignInDto
import com.example.headsandhandstest.kernel.application.ValidationException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class AuthorizationInteractorTest {
    private val authorizationInteractor =
        AuthorizationInteractorFactory().create(MOCK_RESPONSE)

    companion object {
        private const val MOCK_RESPONSE = "Moscow 21"
        private const val CORRECT_EMAIL = "qwe@rew.co"
        private const val CORRECT_PASSWORD = "Qw4sdE"
    }

    @Test
    fun signIn() {
        runBlocking {
            val actualResponse =
                authorizationInteractor.signIn(SignInDto(CORRECT_EMAIL, CORRECT_PASSWORD))

            Assert.assertEquals(MOCK_RESPONSE, actualResponse)
        }
    }

    @Test(expected = ValidationException::class)
    fun noCorrectSignIn() {
        runBlocking {
            authorizationInteractor.signIn(SignInDto("", ""))
        }
    }
}