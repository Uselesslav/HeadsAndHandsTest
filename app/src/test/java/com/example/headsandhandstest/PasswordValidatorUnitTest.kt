package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.PasswordValidationError
import com.example.headsandhandstest.authorization.application.PasswordValidator
import org.junit.Assert
import org.junit.Test

// TODO: Подумать о том, как протестировать большее кол-во кейсов
class PasswordValidatorUnitTest {
    private val passwordValidator = PasswordValidator()

    companion object {
        private const val CORRECT_PASSWORD: String = "qeRT12"
    }

    @Test
    fun withoutCapitalLetter() {
        val expectedErrors = arrayOf(PasswordValidationError.NOT_CONTAIN_CAPITAL_LETTER)
        val actualErrors =
            passwordValidator.getValidationErrors(CORRECT_PASSWORD.toLowerCase()).toTypedArray()

        Assert.assertArrayEquals(expectedErrors, actualErrors)
    }

    @Test
    fun withoutLowercaseLetter() {
        val expectedErrors = arrayOf(PasswordValidationError.NOT_CONTAIN_LOWERCASE_LETTER)
        val actualErrors =
            passwordValidator.getValidationErrors(CORRECT_PASSWORD.toUpperCase()).toTypedArray()

        Assert.assertArrayEquals(expectedErrors, actualErrors)
    }

    @Test
    fun lessThanSixCharacters() {
        val expectedErrors = arrayOf(PasswordValidationError.NOT_CONTAIN_AT_LEAST_SIX_CHARACTERS)
        val actualErrors =
            passwordValidator.getValidationErrors(CORRECT_PASSWORD.take(5)).toTypedArray()

        Assert.assertArrayEquals(expectedErrors, actualErrors)
    }

    @Test
    fun withoutDigits() {
        val stringWithoutDigits = CORRECT_PASSWORD.replace(Regex("\\d"), "q")

        val expectedErrors = arrayOf(PasswordValidationError.NOT_CONTAIN_DIGIT)
        val actualErrors =
            passwordValidator.getValidationErrors(stringWithoutDigits).toTypedArray()

        Assert.assertArrayEquals(expectedErrors, actualErrors)
    }

    @Test
    fun allErrors() {
        val expectedErrors = PasswordValidationError.values()
        val actualErrors =
            passwordValidator.getValidationErrors("").toTypedArray()

        expectedErrors.sortBy { it.name }
        actualErrors.sortBy { it.name }

        Assert.assertArrayEquals(expectedErrors, actualErrors)
    }

    @Test
    fun withoutErrors() {
        val expectedErrors = arrayOf<PasswordValidationError>()
        val actualErrors =
            passwordValidator.getValidationErrors(CORRECT_PASSWORD).toTypedArray()

        Assert.assertArrayEquals(expectedErrors, actualErrors)
    }
}