package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.EmailValidator
import com.example.headsandhandstest.authorization.application.PasswordValidator
import com.example.headsandhandstest.authorization.application.SignInDtoValidator

class SignInDtoValidatorFactory {
    fun create() = SignInDtoValidator(EmailValidator(), PasswordValidator())
}