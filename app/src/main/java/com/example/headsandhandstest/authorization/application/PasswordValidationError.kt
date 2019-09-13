package com.example.headsandhandstest.authorization.application

enum class PasswordValidationError {
    NOT_CONTAIN_AT_LEAST_SIX_CHARACTERS,
    NOT_CONTAIN_CAPITAL_LETTER,
    NOT_CONTAIN_LOWERCASE_LETTER,
    NOT_CONTAIN_DIGIT
}