package com.example.headsandhandstest.authorization.application

import com.example.headsandhandstest.kernel.application.ValidationException

class AuthorizationInteractor(
    private val weatherRepository: WeatherRepository,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {

    suspend fun signIn(signInDto: SignInDto): String {
        validate(signInDto)
        return weatherRepository.get()
    }

    private fun validate(signInDto: SignInDto) {
        val validationException =
            ValidationException()

        emailValidator.getValidationErrors(signInDto.email)
            .forEach { validationException.addError(it) }
        passwordValidator.getValidationErrors(signInDto.password)
            .forEach { validationException.addError(it) }

        validationException.throwIfErrorsNotEmpty()
    }
}