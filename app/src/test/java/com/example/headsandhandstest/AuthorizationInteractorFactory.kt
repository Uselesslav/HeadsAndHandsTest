package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.AuthorizationInteractor

class AuthorizationInteractorFactory {
    fun create(mockResponse: String = "QWERT") = AuthorizationInteractor(
        WeatherRepositoryMockImplementation(mockResponse),
        SignInDtoValidatorFactory().create()
    )
}