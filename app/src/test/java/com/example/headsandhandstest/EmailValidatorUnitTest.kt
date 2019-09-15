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
        val expectedErrors = EmailValidationError.values()
        val actualErrors = mutableSetOf<EmailValidationError>()

        actualErrors.addAll(emailValidator.getValidationErrors("qwr@dw"))
        actualErrors.addAll(emailValidator.getValidationErrors("qwr@.com"))
        actualErrors.addAll(emailValidator.getValidationErrors("@.com"))
        actualErrors.addAll(emailValidator.getValidationErrors("@com"))
        actualErrors.addAll(emailValidator.getValidationErrors(".com"))
        actualErrors.addAll(emailValidator.getValidationErrors("qwrdw"))
        actualErrors.addAll(emailValidator.getValidationErrors("qw.rdw"))
        actualErrors.addAll(emailValidator.getValidationErrors(""))

        Assert.assertArrayEquals(expectedErrors, actualErrors.toTypedArray())
    }
}