package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.SignInDto
import com.example.headsandhandstest.kernel.application.ValidationException
import org.junit.Test

class SignInDtoValidatorTest {
    private val signInDtoValidator = SignInDtoValidatorFactory().create()

    companion object {
        private const val CORRECT_EMAIL = "qwe@rew.co"
        private const val CORRECT_PASSWORD = "Qw4sdE"
    }

    @Test
    fun validateCorrectDto() {
        signInDtoValidator.validate(SignInDto(CORRECT_EMAIL, CORRECT_PASSWORD))
    }

    @Test(expected = ValidationException::class)
    fun validateWithNotValidPassword() {
        signInDtoValidator.validate(SignInDto(CORRECT_EMAIL, ""))
    }

    @Test(expected = ValidationException::class)
    fun validateWithNotValidEmail() {
        signInDtoValidator.validate(SignInDto("", CORRECT_PASSWORD))
    }

    @Test(expected = ValidationException::class)
    fun validateNotValidDto() {
        signInDtoValidator.validate(SignInDto("", ""))
    }
}