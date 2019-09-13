package com.example.headsandhandstest.authorization.application

import java.util.*

class PasswordValidator {
    fun getValidationErrors(password: String): List<PasswordValidationError> {
        val errors = mutableListOf<PasswordValidationError>()

        if (password == password.toLowerCase(Locale.getDefault())) {
            errors.add(PasswordValidationError.NOT_CONTAIN_CAPITAL_LETTER)
        }
        if (password == password.toUpperCase(Locale.getDefault())) {
            errors.add(PasswordValidationError.NOT_CONTAIN_LOWERCASE_LETTER)
        }
        if (password.length < 6) {
            errors.add(PasswordValidationError.NOT_CONTAIN_AT_LEAST_SIX_CHARACTERS)
        }
        if (!password.contains(Regex("[0-9]"))) {
            errors.add(PasswordValidationError.NOT_CONTAIN_DIGIT)
        }

        return errors
    }
}