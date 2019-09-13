package com.example.headsandhandstest.authorization.application

import com.example.headsandhandstest.kernel.application.ValidationException

class SignInDtoValidator(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {

    fun validate(signInDto: SignInDto) {
        val validationException =
            ValidationException()

        emailValidator.getValidationErrors(signInDto.email)
            .forEach { validationException.addError(it) }
        passwordValidator.getValidationErrors(signInDto.password)
            .forEach { validationException.addError(it) }

        validationException.throwIfErrorsNotEmpty()
    }
}