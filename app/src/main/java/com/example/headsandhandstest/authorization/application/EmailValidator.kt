package com.example.headsandhandstest.authorization.application

class EmailValidator {
    fun getValidationErrors(email: String): List<EmailValidationError> {
        val errors = mutableListOf<EmailValidationError>()

        if (!email.matches(Regex("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$"))) {
            errors.add(EmailValidationError.NOT_VALID)
        }

        return errors
    }
}
