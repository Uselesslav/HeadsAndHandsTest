package com.example.headsandhandstest.authorization.application

import com.example.headsandhandstest.kernel.application.ValidationException
import kotlinx.coroutines.delay
import java.util.*

class AuthorizationInteractor {
    suspend fun signIn(signInDto: SignInDto): String {
        delay(1000)
        validate(signInDto)

        return "Погода дададада"
    }

    private fun validate(signInDto: SignInDto) {
        val validationException =
            ValidationException()

        // TODO: Move strings checking to another class
        if (!signInDto.email.matches(Regex("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$"))) {
            validationException.addError(SignInValidationError.EMAIL_NOT_VALID)
        }
        if (signInDto.password == signInDto.password.toLowerCase(Locale.getDefault())) {
            validationException.addError(SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_CAPITAL_LETTER)
        }
        if (signInDto.password == signInDto.password.toUpperCase(Locale.getDefault())) {
            validationException.addError(SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_LOWERCASE_LETTER)
        }
        if (signInDto.password.length < 6) {
            validationException.addError(SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_SIX_CHARACTERS)
        }
        if (!signInDto.password.contains(Regex("[0-9]"))) {
            validationException.addError(SignInValidationError.PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_DIGIT)
        }

        validationException.throwIfErrorsNotEmpty()
    }
}
