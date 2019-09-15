package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.EmailValidationError
import com.example.headsandhandstest.authorization.application.EmailValidator
import org.junit.Assert
import org.junit.Test

// TODO: Подумать о том, как протестировать большее кол-во кейсов
class EmailValidatorUnitTest {
    private val emailValidator = EmailValidator()

    companion object {
        private const val CORRECT_EMAIL = "qwe@sdf.csd"
    }

    @Test
    fun withoutErrors() {
        val expectedErrors = arrayOf<EmailValidationError>()
        val actualErrors = emailValidator.getValidationErrors(CORRECT_EMAIL).toTypedArray()

        Assert.assertArrayEquals(expectedErrors, actualErrors)
    }

    @Test
    fun notValidEmail() {
        val actualErrors = mutableListOf<EmailValidationError>()

        val listNotValidEmails =
            listOf("qwr@dw", "qwr@.com", "@.com", "@com", ".com", "qwrdw", "qw.rdw", "")

        listNotValidEmails.forEach {
            actualErrors.addAll(emailValidator.getValidationErrors(it))
        }

        Assert.assertEquals(listNotValidEmails.size, actualErrors.size)
        Assert.assertTrue(actualErrors.all { it == EmailValidationError.NOT_VALID })
    }
}